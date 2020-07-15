package com.jsp.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolver {
	
	public static void view(HttpServletRequest request, HttpServletResponse response, String url) 
	throws ServletException, IOException{
		
		//url이 비었을 때는 return
		if(url==null) {return;}
		
		if(url.indexOf("redirect:") > -1) {
			
			//redirect하기. 항상 서블렛으로 보내준다. 즉 request를 리셋한다.
			
			url = request.getContextPath()+url.replace("redirect:", "");
			response.sendRedirect(url);
			
		}else {
			
			//forward하기. 항상 화면 전환이기에 jsp로 보내준다.
			
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			url = prefix + url + suffix;
			request.getRequestDispatcher(url).forward(request, response);
			
		}
		
	}
	
}
