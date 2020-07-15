package com.jsp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.MemberVO;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/common/loginForm.jsp")
			.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/WEB-INF/views/common/login_success.jsp";
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//로그인 처리부
		//MemberVO member = mService.getMember();
		
		if(!(id.equals("mimi") && pwd.equals("mimi"))) {
			request.setAttribute("id", id);
			url = "/WEB-INF/views/common/loginForm.jsp";
		}else {
			
			//로그인 성공
			MemberVO member = new MemberVO();
			member.setId(id);
			member.setPwd(pwd);
			member.setEmail("mimi@mimi.com");
			member.setPhone("000-0000-0000");
			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", member);
			
		}
		request.getRequestDispatcher(url)
			.forward(request, response);
	}
	
}

