package com.pumsac.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pumsac.command.MeetingRoomJusoCommand;
import com.pumsac.command.SearchCriteria;
import com.pumsac.service.AdminService;
import com.pumsac.service.ShowRoomsService;
import com.pumsac.vo.JusoVO;
import com.pumsac.vo.MeetingRoomVO;

@Controller
@RequestMapping("/dsp/admin/location/*")
public class AdminRoomLocationController {
	
	//회원관리, 사무실관리(사무실관제), 이용안내, 결제내역
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ShowRoomsService showRoomService;
	
	@Resource(name = "meetingRoomPictures")
    String uploadPath;
	
	@RequestMapping(value="regist",method=RequestMethod.GET)
	public ModelAndView getRegistMeetingroomInfo(ModelAndView mnv) {
		String url = "site/admin/location/regist";
		
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping(value="regist",method=RequestMethod.POST)
	public ModelAndView registMeetingroomInfo(ModelAndView mnv,
									MeetingRoomJusoCommand room) {
		String url = "site/admin/location/regist_success";
		
		try {
			adminService.registMeetingRoom(room);
		} catch (Exception e) {
			url = "/error/500_error";
			e.printStackTrace();
		}
		
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping("list") //사무실 리스트 보기
	public ModelAndView getLocationList(ModelAndView mnv,SearchCriteria cri) {
		String url = "site/admin/location/list";
		
		Map<String, Object> map = null;
		try {
			map = adminService.getMeetingRoomList(cri);
		} catch (Exception e) {
			e.printStackTrace();
			mnv.setViewName("/error/500_error");
			return mnv;
		}
		
		mnv.addAllObjects(map);
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping("detail/{roomNo}")
	public ModelAndView getLocationDetail(ModelAndView mnv, @PathVariable("roomNo") int roomNo) {
		String url = "site/admin/location/detail";
		MeetingRoomJusoCommand room = null;
		
		try {
			room = adminService.getMeetingRoomDetail(roomNo);
		} catch (Exception e) {
			e.printStackTrace();
			mnv.setViewName("/error/500_error");
			return mnv;
		}
		mnv.addObject("room",room);
		mnv.setViewName(url);
		return mnv;
	}
	
	//사무실 수정시 보여줄 화면
	@RequestMapping(value="modify/{roomNo}", method=RequestMethod.GET)
	public ModelAndView getLocaionModifyPage(ModelAndView mnv, @PathVariable("roomNo") int roomNo) {
		String url = "site/admin/location/modify";
		MeetingRoomJusoCommand room = null;
		
		//사무실 관련 사항들 가져오기 (커맨드객체 사용)
		try {
			room = adminService.getMeetingRoomDetail(roomNo);
		} catch (Exception e) {
			e.printStackTrace();
			mnv.setViewName("/error/500_error");
			return mnv;
		}

		//검색시 관련된 하위메뉴 가져오기
		JusoVO juso = new JusoVO();
		juso.setCity(room.getCity());
		List<JusoVO> jusoArea = null;
		
		try {
			jusoArea = showRoomService.getJusoMasterArea(juso);
		} catch (Exception e) {
			e.printStackTrace();
			mnv.setViewName("/error/500_error");
			return mnv;
		}
		mnv.addObject("jusoArea", jusoArea);
		mnv.addObject("room",room);
		mnv.setViewName(url);
		return mnv;
	}
	

	
	//사무실 내용 수정
	@RequestMapping(value="modify/{roomNo}", method=RequestMethod.POST)
	public ModelAndView modifyMeetingroomInfo(ModelAndView mnv, MeetingRoomJusoCommand room)  {
		String url = "site/admin/location/modify_success";
		try {
			adminService.modifyMeetingRoom(room);
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		mnv.addObject("roomNo", room.getRoomNo());
		mnv.setViewName(url);
		return mnv;
	}
	
/*	//첨부파일 삭제 ajax로 온다
	@RequestMapping(value="removeAttach")
	@ResponseBody
	public ResponseEntity<String> removeAttachFile(int no){
		ResponseEntity<String> entity = null;
		try {
			adminService.removeAttachFile(no);
			entity=new ResponseEntity<String>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}*/
	
	
	//사무실 내용 삭제
	@RequestMapping(value="remove/{roomNo}")
	public ModelAndView removeMeetingRoomInfo(@PathVariable("roomNo")int roomNo, String deleteyn, ModelAndView mnv) {
		String url = "site/admin/location/remove_success";
		System.out.println(deleteyn);
		MeetingRoomVO meetingRoom = new MeetingRoomVO();
		meetingRoom.setNo(roomNo);
		meetingRoom.setDeleteyn(deleteyn);
		
		try {
			adminService.removeMeetingRoom(meetingRoom);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mnv.addObject("roomNo",roomNo);
		mnv.setViewName(url);
		return mnv;
	}
	
	
	//사무실 그림 보여주기
	@RequestMapping("picture")
	@ResponseBody
	public ResponseEntity<byte[]> getMeetingRoomPictures(String fileName) {
		InputStream in = null;
//		HttpHeaders headers = new HttpHeaders();
//		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
//		MediaType mType = MediaUtils.getMediaType(formatName);
		ResponseEntity<byte[]> entity = null;	//이미지 파일을 byte스트림으로 내보내기
				
		try {
			in = new FileInputStream(uploadPath + File.separator + fileName);
//			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
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
