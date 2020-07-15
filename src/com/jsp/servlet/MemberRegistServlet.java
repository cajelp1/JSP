package com.jsp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.MemberVO;
import com.jsp.request.MemberRegistRequest;
import com.jsp.service.MemberServiceImpl;
import com.jsp.utils.ViewResolver;

/*@WebServlet("/member/regist")*/
public class MemberRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/regist";
		
		ViewResolver.view(request, response, url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "member/regist_success";
		
		String id        = request.getParameter("id");
		String pwd       = request.getParameter("pwd");
		String name      = request.getParameter("name");
		String email     = request.getParameter("email");
		String picture   = request.getParameter("picture");
		String[] phone     = request.getParameterValues("phone");
		String authority = request.getParameter("authority");
		
		MemberRegistRequest memReq = new MemberRegistRequest();
		
		MemberVO member = memReq.toMemberVO();
		
		try {
			MemberServiceImpl.getInstance().regist(member);
		} catch (SQLException e) {
			e.printStackTrace();
			url="member/regist_fail";
		}
		
		ViewResolver.view(request, response, url);
	}

}
