package com.jsp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dispatcher.ViewResolver;
import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;
import com.jsp.service.MemberServiceImpl;



/*@WebServlet("/commons/login")*/
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	public void init(ServletConfig config) throws ServletException {
//		System.out.println("init() execute");
//	}

//	public void destroy() {
//		System.out.println("destory() execute");
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("doGet() execute");
		
		String url="/WEB-INF/views/commons/loginForm.jsp";
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("doPost() execute");
		
		String url="redirect:/member/list";
		
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		
		HttpSession session = request.getSession();
		
		try {
			MemberServiceImpl.getInstance().login(id, pwd);
			
			MemberVO loginUser = MemberServiceImpl.getInstance().getMember(id);
			//System.out.println("LLL ==> " + loginUser);
			session.setAttribute("loginUser", loginUser);
			session.setMaxInactiveInterval(20*60);
			
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


