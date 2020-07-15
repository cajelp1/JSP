package com.jsp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;
import com.jsp.service.MemberService;
import com.jsp.service.MemberServiceImpl;
import com.jsp.utils.ViewResolver;


/*@WebServlet("/commons/login")*/
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "commons/loginForm";
		ViewResolver.view(request, response, url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "redirect:/member/list";
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		HttpSession session = request.getSession();
		
		try {
			MemberServiceImpl.getInstance().login(id, pwd);
			MemberVO loginUser = MemberServiceImpl.getInstance().getMember(id);
			
			session.setAttribute("loginUser", loginUser);
			session.setMaxInactiveInterval(20 * 60);
			
			System.out.println("로그인 했을때 : "+loginUser);
			
		} catch (SQLException e) {
			e.printStackTrace();
			url="error/500_error";
			request.setAttribute("exception", e);
			
		} catch (NotFoundIDException | InvalidPasswordException e) {
			e.printStackTrace();
			url="commons/loginForm";
			request.setAttribute("msg", e.getMessage());
			
		}
		
		ViewResolver.view(request, response, url);
	}
	
}
