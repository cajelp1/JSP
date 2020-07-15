package com.jsp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dispatcher.ViewResolver;
import com.jsp.dto.MemberVO;

public class MemberDisabledFilter implements Filter {
	
	//private Set<String> inURLs = new HashSet<String>();
	private List<String> inURLs=new ArrayList<String>();
	private ViewResolver viewResolver;
	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpSession session = httpReq.getSession();
		//forward로 보내줄때 httpservletResponse도 필요하니까 꺼내놓쟈
		HttpServletResponse httpRes = (HttpServletResponse) response;
		
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		//체크할 url을 고려한다
		String reqUrl = httpReq.getRequestURI().substring(httpReq.getContextPath().length());
		
/* 		//내가 짠 코드		
		if(includeCheck(reqUrl)) {	//체크 대상인 url이고		
			
			if(loginUser.getEnabled()==0) {	//로그인 대상자가 enabled=0인 경우
				String url = "commons/disabledCheck";
				ViewResolver.view(httpReq, httpRes, url);
				return;
			}else {	
				chain.doFilter(request, response);
				return;
			}
		}
		
		//url이 해당하지 않을 때는 다음으로!
		chain.doFilter(request, response);
*/
		
		//교수님 코드. hashset을 이용... 하려고 했지만 uri가 너무 복잡허다 ㅠㅠㅋㅋㅋ
		//그리고 loginUser를 비교하는게 url이 올 때마다 비교하는 것보다 간단하다고 보심.
		
		if(loginUser!=null && loginUser.getEnabled()==0) {
			for(String url : inURLs) {
				if(reqUrl.contains(url)){
					url = "commons/disabledCheck";
					viewResolver.view(httpReq, httpRes, url);
					return;
				}
			}
		}
		chain.doFilter(request, response);
		
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		//regist, modify, remove, disabled를 거르자!
		
		String includeURLNames = fConfig.getInitParameter("include");
		StringTokenizer st = new StringTokenizer(includeURLNames, ",");
		while(st.hasMoreTokens()) {
			inURLs.add(st.nextToken());
		}
		System.out.println(inURLs.toString());
		
		String viewResolverType = fConfig.getInitParameter("viewResolver");
		
		try {
			Class<?> viewResolver = Class.forName(viewResolverType);
			this.viewResolver = (ViewResolver) viewResolver.newInstance();
			System.out.println("disabled 필터에서 viewResovler 만듦");
		} catch (Exception e) {
			System.out.println("disabled 필터에서 viewResovler 안만르어짐!!!");
			e.printStackTrace();
		}
		
	}
	
	private boolean includeCheck(String url) {
		for(String inURL : inURLs) {
			if(url.contains(inURL)) {
				return true;
			}
		}
		return false;
	}
}

