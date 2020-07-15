package com.jsp.action.commons;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class LoginAction implements Action {
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "redirect:/board/list.do";
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		try {
			HttpSession session = request.getSession();
			MemberVO loginUser = memberService.login(id, pwd);
			if (loginUser == null || !pwd.equals(loginUser.getPwd())) {			
				url="commons/loginForm";
				session.setAttribute("msg", "아이디가 없거나 패스워드가 일치하지 않습니다.");
				return url;
			}else {
				session.setAttribute("loginUser", loginUser);
				session.setMaxInactiveInterval(60 * 60);
			}
		} catch (SQLException e) {
			url="error/500_error";
			e.printStackTrace();
		}
		
		return url;
	}

}
