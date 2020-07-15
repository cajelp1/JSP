package com.jsp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.test.Parent;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int a = Integer.parseInt(request.getParameter("a"));
		int b = Integer.parseInt(request.getParameter("b"));
		
		Parent parent = Parent.getInstance();
		
		String result = "";
		
		try {
			result = parent.resultSum(a, b);
		} catch (Exception e) {
			System.out.println("오류가 발생했습니다.");
			e.printStackTrace();
		}
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
