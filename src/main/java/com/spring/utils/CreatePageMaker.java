package com.spring.utils;

import javax.servlet.http.HttpServletRequest;

import com.spring.request.PageMaker;
import com.spring.request.SearchCriteria;

public class CreatePageMaker {
	
	public static PageMaker make(HttpServletRequest request) throws Exception {
		
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageNum = Integer.parseInt(request.getParameter("perPageNum"));
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		
		PageMaker pageMaker = new PageMaker();
		
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(page);
		cri.setPerPageNum(perPageNum);
		cri.setKeyword(keyword);
		cri.setSearchType(searchType);
		
		pageMaker.setCri(cri);
		
		
		return pageMaker;
	}
	
	
	
}
