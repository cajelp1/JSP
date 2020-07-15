package com.jsp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.MemberVO;
import com.jsp.service.ILoginService;
import com.jsp.service.LoginServiceImpl;

/**
 * Servlet implementation class loginServlet
 */
/*@WebServlet("/loginServlet")*/
public class LoginServlet extends HttpServlet {
	
	private static ILoginService service;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/common/loginForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/WEB-INF/views/common/login_success.jsp";
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPwd(pwd);
		
		ILoginService service = LoginServiceImpl.getInstance();
		
		MemberVO member = null;
		
		member = service.isLogin(vo);
		
		
		//이 아래는 위의 코드를 못 써서 임시로 만듦////////////////
		/*
		boolean a = false;
		
		MemberVO member = null;
		
		if(a) {
			//로그인 성공
			member = new MemberVO();
			member.setId("sarahlee");
			member.setEmail("sarahlee@gmail.com");
			member.setPhone("000-0000-0000");
			member.setAddress("대전 북구 모여봐요동 물의숲");
			member.setName("이예림");
			member.setAuthority("GeneralUser");
			member.setEnabled(1);
		}else {
			//로그인 실패
		}
		*/
		//////////////////////////////////////////////
		
		
		if(member == null) {
			
			request.setAttribute("id", id);
			url = "/WEB-INF/views/common/loginForm.jsp";
			
		} else {
			
			//로그인 성공
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", member);
			
		}
		//response.sendRedirect("/main");
		request.getRequestDispatcher(url).forward(request, response);
	}
	
}
