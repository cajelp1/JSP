package com.jsp.dispatcher;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;

public class FrontServlet extends HttpServlet {
	
	private HandlerMapper handlerMapper;
	private ViewResolver viewResolver;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		
		String handlerMapperType = config.getInitParameter("handlerMapper");
		String viewResolverType = config.getInitParameter("viewResolver");
		
		try {
			handlerMapper = (HandlerMapper)injeactionBean(handlerMapperType);
			viewResolver = (ViewResolver)injeactionBean(viewResolverType);
		}catch(Exception e) {
			System.out.println("frontServlet에서 핸드러인지 뷰리솗인지 암튼 안됨 뭔가 안됨");
			e.printStackTrace();
		}
		
		super.init(config);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command = request.getRequestURI(); //contextPath 포함
		
		if(command.indexOf(request.getContextPath())==0) { //contextPath 삭제
			command = command.substring(request.getContextPath().length());
		}
		
		Action act = null;
		String view = null;
		
		act = handlerMapper.getAction(command);
		
		if(act==null) {
			System.out.println(command+"를 찾을 수 없습니다");
		}else {
			view = act.execute(request, response);
			
			if(view!=null) {
				viewResolver.view(request, response, view);
			}
		}
	}
	
	private Object injeactionBean(String classType) throws Exception {
		
		Class<?> type = Class.forName(classType);
		return type.newInstance();
		
	}
	
}
