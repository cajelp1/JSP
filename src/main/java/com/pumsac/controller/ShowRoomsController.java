package com.pumsac.controller;

import com.pumsac.command.CriteriaRoomList;
import com.pumsac.dao.PaymentDao;
import com.pumsac.service.PaymentService;
import com.pumsac.service.ShowRoomsService;
import com.pumsac.vo.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller

@RequestMapping("/dsp/room/*")
public class ShowRoomsController {
	
    @Autowired
    private ShowRoomsService showRoomsService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentDao paymentDao;
    
    @Resource(name = "meetingRoomPictures")
    String uploadPath;
    
    @RequestMapping(value = "/getJusoArea", method = RequestMethod.POST)
    public ResponseEntity<List<JusoVO>> getJusoMasterArea(String city) {

        ResponseEntity<List<JusoVO>> entity = null;

        List<JusoVO> jusoArea = null;
        JusoVO juso = new JusoVO();
        juso.setCity(city);

        try {
            jusoArea = showRoomsService.getJusoMasterArea(juso);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<List<JusoVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
            return entity;
        }
        entity = new ResponseEntity<List<JusoVO>>(jusoArea, HttpStatus.OK);
        return entity;
    }
    
    @RequestMapping("list")
    public ModelAndView showRoomList(ModelAndView mnv, CriteriaRoomList cri) throws Exception {
        String url = "room/list";
        //CriteriaRoomList cri = new CriteriaRoomList();

        Map<String, Object> map = showRoomsService.getMeetingRooms(cri);

        mnv.addAllObjects(map);
        mnv.setViewName(url);

        return mnv;
    }
    
    @RequestMapping(value = "getMeetingRooms/{page}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getMeetingRooms(
            @RequestBody CriteriaRoomList cri,
            @PathVariable("page") int page) {

        cri.setPage(page);
        System.out.println(cri.getCity());
        System.out.println(cri.getArea());
        System.out.println(cri.getCapacity());

        ResponseEntity<Map<String, Object>> entity = null;
        try {
            Map<String, Object> map = showRoomsService.getMeetingRooms(cri);
            entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception e) {
            entity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }

        return entity;
    }
    
    @RequestMapping("detail/{no}")
    public ModelAndView showRoomDetail(ModelAndView mnv, @PathVariable("no") int no, HttpSession session) throws SQLException {
        String url = "room/detail";
        MeetingRoomVO room = null;
        List<MeetingRoomAttachFileVO> imgList = null;
        MeetingRoomAttachFileVO profileImg = null;
        
        try {
            room = showRoomsService.getMeetingRoomDetail(no);
            imgList = showRoomsService.getMeetingRoomPictures(no);
            
            for(int i = 0; i < imgList.size(); i++) {
            	if(imgList.get(i).getProfileyn().equals("y")) {
            		profileImg = imgList.get(i);
            		imgList.remove(i);
            	}
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            mnv.setViewName("error/500_error");
            return mnv;
        }

		mnv.addObject("room", room);
		mnv.addObject("profileImg", profileImg);
		mnv.addObject("imgList", imgList);

		mnv.setViewName(url);
		return mnv;
    }
    
    /*
     *
     * 로그인시 장소찾기 후 결제
     *
     *
     * */
    @GetMapping("reservation/{no}")
    public ModelAndView reservation(@PathVariable("no") int no, ModelAndView modelAndView, HttpSession session) throws SQLException {
        modelAndView.setViewName("/room/room_reservation");
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        
        List<Map<String, String>> list = paymentService.reservationHistory(no);
        List<ProjectVO> projectList=paymentDao.selectProjectInfoById(loginUser);
        modelAndView.addObject("reservationHistory", list);
        modelAndView.addObject("projectList",projectList);
        modelAndView.addObject("no",no);

        return modelAndView;
    }
    
    @PostMapping("reservation.do")
        public String  doReservation(PaymentHistoryVO paymentHistoryVO, Model model, HttpSession session, HttpServletResponse response) throws ParseException, InterruptedException, SQLException, IOException {
        int result=paymentDao.selectReservationCheckByMeetingRoomNo(paymentHistoryVO);
        String url="";
        if(result==0) {
            model.addAttribute("startDate", paymentHistoryVO.getStartDate());
            model.addAttribute("endDate", paymentHistoryVO.getEndDate());
            MemberVO loginUserInfo = (MemberVO) session.getAttribute("loginUser");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = simpleDateFormat.parse(paymentHistoryVO.getStartDate());
            Date endDate = simpleDateFormat.parse(paymentHistoryVO.getEndDate());
            long diffDay = (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
            if(startDate==endDate)
                diffDay=1;
           model.addAttribute("pricePerDay",paymentDao.selectPricePerDayByno(paymentHistoryVO.getNo()));
            model.addAttribute("diffDay", diffDay+1);
            model.addAttribute("loginUser", loginUserInfo);
            model.addAttribute("roomNumber",paymentHistoryVO.getNo());
            model.addAttribute("projectId",paymentHistoryVO.getProjectId());
            model.addAttribute("msg","결제를 완료하면 예약이 됩니다.");
            url="room/initPayAPI";

        }
        else{

            PrintWriter writer =response.getWriter();
            writer.println("<script>");
            writer.println("alert('해당 날짜에 이미 예약되어있습니다. 다른 날짜를 선택해 주세요)");
            writer.println("window.history.go(-1)");
            writer.println("/<script>");

        }
        return url;

    }
    
    @GetMapping("resultPayment")
    public String resultPayment(@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate,@RequestParam("roomNumber") int roomNumber,@RequestParam("amount") int amount,@RequestParam("projectId") String projectId, HttpSession session) throws SQLException {
        MemberVO loginUser=(MemberVO) session.getAttribute("loginUser");
        PaymentHistoryVO vo =new PaymentHistoryVO();
        vo.setMeetingNo(roomNumber);
        vo.setStartDate(startDate);
        vo.setEndDate(endDate);
        vo.setAmount(amount);
        vo.setRegister(loginUser.getEmail());
        vo.setProjectId(projectId);
        vo.setNo(paymentDao.getReservateSeqNetxval());
        vo.setProjectId(projectId);
        paymentService.reigistPaymentHistory(vo);
        //  System.out.println("결제완료후 redirecturl 들어왔나요?");
        return "room/reservation_success";
    }
    
	//사무실 프로필(대표사진) 보여주기
	@RequestMapping("profile")
	@ResponseBody
	public ResponseEntity<byte[]> getMeetingRoomProfilePicture(int room) {
		System.out.println(room);
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;	//이미지 파일을 byte스트림으로 내보내기
		MeetingRoomAttachFileVO profile = null;
		try {
			profile = showRoomsService.getOfficeProfilePicture(room);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String fileName = profile.getFileName();
		
		try {
			in = new FileInputStream(uploadPath + File.separator + fileName);
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
					HttpStatus.CREATED);
			
		}catch(IOException e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}finally {
			try {
				if(in != null)in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return entity;
	}
	
	//사무실 그림 전부 보여주기
	@RequestMapping("picture")
	@ResponseBody
	public ResponseEntity<byte[]> getMeetingRoomPictures(String fileName) {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;	//이미지 파일을 byte스트림으로 내보내기
		
		System.out.println("fileName : "+fileName);
		
		try {
			in = new FileInputStream(uploadPath + File.separator + fileName);
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
					HttpStatus.CREATED);
		}catch(IOException e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}finally {
			try {
				if(in != null)in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return entity;
	}
	
}
