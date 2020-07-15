package com.pumsac.controller.site;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pumsac.command.RegistMemberCommand;
import com.pumsac.service.AttachFileService;
import com.pumsac.service.MemberService;
import com.pumsac.service.PaymentService;
import com.pumsac.util.PasswordEncryption;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.ReservationForMyPageVO;

@Controller
@RequestMapping("/dsp/my")
public class MyPageController {

	@Autowired
	private MemberService memberService;
	
    @Autowired
    private AttachFileService attachFileService;
	
	@Autowired
	private PaymentService payService;
	
	@RequestMapping("")
	public ModelAndView myPageMain(HttpSession session) {
		ModelAndView mnv = new ModelAndView();
		String url = "/commons/mypage/main";
		
		MemberVO member = (MemberVO) session.getAttribute("loginUser");
//		if(member == null) {
//			url = "/commons/mypage/noFoundLoginUser";
//		}
		
		mnv.addObject("member", member);
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping("detail")
	public ModelAndView memberDetail(ModelAndView mnv, HttpSession session, HttpServletResponse response) {

		String url = "/commons/mypage/detail";
		
		MemberVO member = (MemberVO) session.getAttribute("loginUser");
		if(member == null) {
			url = "/commons/mypage/noFoundLoginUser";
		}
		
		mnv.addObject("member", member);
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping(value="/modify")
	public ModelAndView getModifyMember (ModelAndView mnv, HttpSession session) {
		String url = "/commons/mypage/modify";
		MemberVO member = (MemberVO) session.getAttribute("loginUser");
		if(member == null) {
			url = "/commons/mypage/noFoundLoginUser";
		}
		
		mnv.addObject("member", member);
		mnv.setViewName(url);
		return mnv;
	}
	
	@PostMapping(value="/modify")
	public ModelAndView postModifyMember (RegistMemberCommand command, HttpSession session, ModelAndView mnv) {
		String url = "/commons/mypage/modify_success";

		try {
			memberService.modifyMemberInfo(command.toMemberVO());
			//첨부파일 변경 여부 있는지 체크
			if(command.getPicture() != "") {
				attachFileService.modifyProfileFile(command.toAttachFileVO());				
			}
		} catch (SQLException e) {
			url = "/error/500_error";
		}
//		session.setAttribute("loginUser", command);
		session.invalidate();
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping(value="/removeCheck")
	public ModelAndView getRemoveMember(HttpSession session) {
		ModelAndView mnv = new ModelAndView();
		String url = "/commons/mypage/remove";
		MemberVO member = (MemberVO) session.getAttribute("loginUser");
		
		mnv.addObject("member", member);
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping(value="/removeMember")
	public ModelAndView RemoveMember(String email, HttpSession session) {
		ModelAndView mnv = new ModelAndView();
		String url = "/commons/mypage/remove_success";
		
		try {
			memberService.removeMember(email);
		} catch (SQLException e) {
			url = "/error/500_error";
		}
		session.invalidate();
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping(value="/pay/list")
	public ModelAndView PayList(ModelAndView mnv, HttpSession session) {
		String url = "/commons/mypage/pay/list";
		String email = ((MemberVO) session.getAttribute("loginUser")).getEmail();
		if(email == null) {
			url = "/";
		}
		
		List<ReservationForMyPageVO> reservationList = null;
		try {
			reservationList = memberService.getReservationList(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		mnv.addObject("reservationList",reservationList);
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping(value="/changePassword")
	public ModelAndView getChangePassword(ModelAndView mnv, HttpSession session) {
		String url = "commons/mypage/changePassword";
		
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping(value="/changePassword", method=RequestMethod.POST)
	public ModelAndView changePassword(ModelAndView mnv, HttpSession session, String newPassword, String oldPassword) {
		String url = "commons/mypage/changePassword_success";
		
		try {
			String password = PasswordEncryption.passwordEncryption(newPassword);
			MemberVO vo = (MemberVO) session.getAttribute("loginUser");
			vo.setPassword(password);
			memberService.modifyMemberPassword(vo);
			
			session.invalidate();
		} catch (NoSuchAlgorithmException e) {
			url = "commons/mypage/changePassword_fail";
			e.printStackTrace();
		} catch (Exception e) {
			url = "commons/mypage/changePassword_fail";
			e.printStackTrace();
		}
		
		mnv.setViewName(url);
		return mnv;
	}
	
	@PostMapping(value="/pay/cancel")
	@ResponseBody
	public ResponseEntity<Map<String, Boolean>> cancelPay(int no){
		ResponseEntity<Map<String, Boolean>> entity = null;
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		try {
			payService.requestCancelReservation(no);
			result.put("result", true);
		} catch (SQLException e) {
			e.printStackTrace();
			result.put("result", false);
		}
		entity = new ResponseEntity<Map<String,Boolean>>(result,HttpStatus.OK);
		return entity;
	}
}
