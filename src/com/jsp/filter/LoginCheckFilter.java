package com.jsp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dispatcher.ViewResolver;
import com.jsp.dto.MemberVO;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;


public class LoginCheckFilter implements Filter {
	
	private List<String> exURLs=new ArrayList<String>(); 
	private ViewResolver viewResolver;
	
	public void destroy() {
		
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpReq=(HttpServletRequest)request;
		HttpServletResponse httpResp=(HttpServletResponse)response;
		
		//제외할 url 확인
		String reqUrl=httpReq.getRequestURI()
				.substring(httpReq.getContextPath().length());
		
		if(excludeCheck(reqUrl)) {
			chain.doFilter(request, response);
			return;
		}
		
		HttpSession session = httpReq.getSession();
		
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		//login 확인
		if(loginUser==null) { //비로그인 상태
			String url="commons/loginCheck";
			viewResolver.view(httpReq, httpResp, url);
		}else {
			chain.doFilter(request, response);			
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
		String excludeURLNames=fConfig.getInitParameter("exclude");
		StringTokenizer st= new StringTokenizer(excludeURLNames,",");
		while(st.hasMoreTokens()) {
			exURLs.add(st.nextToken());
		}
		System.out.println(exURLs.get(0));
		System.out.println(exURLs.get(1));
		System.out.println(exURLs.get(2));
		
		String viewResolverType = fConfig.getInitParameter("viewResolver");
		
		try {
			Class<?> viewResolver = Class.forName(viewResolverType);
			this.viewResolver = (ViewResolver) viewResolver.newInstance();
			System.out.println("로그인 필터에서 viewResovler 만듦");
		} catch (Exception e) {
			System.out.println("로그인 필터에서 viewResovler 안만르어짐!!!");
			e.printStackTrace();
		}
		
	}
	
	private boolean excludeCheck(String url) {		
		for(String exURL:exURLs) {
			if(url.contains(exURL)) {
				return true;
			}
		}		
		return false;
	}
	

}
