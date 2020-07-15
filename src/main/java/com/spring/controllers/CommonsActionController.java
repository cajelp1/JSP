package com.spring.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.dto.MemberVO;
import com.spring.exception.InvalidPasswordException;
import com.spring.exception.NotFoundIDException;
import com.spring.service.MemberService;

@Controller
@RequestMapping("/commons/*")
public class CommonsActionController {
	
	@Autowired
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	
	//@RequestMapping(value= {"/","/commons/loginForm.do"})
	@RequestMapping("loginForm.do")
	public String loginForm(Model model) throws Exception{
		String url="commons/loginForm";
		model.addAttribute("title","로그인");
		return url;
	}
	
	
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String login(HttpSession session, String id, String pwd, Model model) throws Exception {
		String url="redirect:/member/list.do";
		//로그인 실패시 추가한 attribute를 삭제
		session.removeAttribute("msg");
		String message = null;
		
		try {
			memberService.login(id, pwd);
			
			MemberVO loginUser = memberService.getMember(id);
			if(loginUser.getEnabled()==0) { //사용정지
				message="사용중지된 아이디로 이용이 제한됩니다.";
				url = "redirec:/commons/loginForm.do";
			}else { //사용가능
				session.setAttribute("loginUser", loginUser);
				session.setMaxInactiveInterval(20*60);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			url="error/500_error";
			model.addAttribute("exception", e);
			
		} catch (NotFoundIDException | InvalidPasswordException e) {
			e.printStackTrace();
			url="redirect:/commons/loginForm.do";
			model.addAttribute("msg", e.getMessage());
		}
		
		session.setAttribute("msg", message);
		session.setAttribute("id", id);
		
		return url;
	}
	
	
	/*
	@RequestMapping("logout.do")
	public String logout(HttpSession session) throws Exception{
		String url = "redirect:/commons/loginForm.do";
		session.invalidate();
		return url;
	}
	*/
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session, Model model) throws Exception{
		String url = "commons/logout";
		session.invalidate();
		model.addAttribute("msg", "로그아웃되었습니다.");
		return url;
	}
	
	
	
	
	
}
