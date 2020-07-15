package com.jsp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dispatcher.ViewResolver;
import com.jsp.dto.MemberVO;

public class LoginCheckFilter implements Filter {
	
	private List<String> exURL = new ArrayList<String>();
	private ViewResolver viewResolver;
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		
		//url을 가져와서 
		String reqURL = hRequest.getRequestURI().substring(hRequest.getContextPath().length());
		
		//로그인 필요없는 화면일 시 넘어감
		if(excludeCheck(reqURL)) {
			chain.doFilter(request, response);
			return;
		}
		
		//그렇지 않을경우 로그인 확인
		HttpSession session = hRequest.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if(loginUser==null) {
			String url = "commons/loginCheck";
			viewResolver.view(hRequest, hResponse, url);
		}else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
		String excludeURLs = fConfig.getInitParameter("exclude");
		StringTokenizer st = new StringTokenizer(excludeURLs,",");
		
		while(st.hasMoreTokens()) {
			exURL.add(st.nextToken());
		}
		
		String viewResolverType = fConfig.getInitParameter("viewResolver");
		
		try {
			Class<?> viewResolver = Class.forName(viewResolverType);
			this.viewResolver = (ViewResolver) viewResolver.newInstance();
			System.out.println("로그인 필터에서 viewResolver 생성 완료");
		}catch(Exception e) {
			System.out.println("로그인 필터에서 viewResolver 생성 실패");
			e.printStackTrace();
		}
	}
	
	private boolean excludeCheck(String url) {
		for(String exurl : exURL) {
			if(url.contains(exurl)) {
				return true;
			}
		}
		return false;
	}
	
}
