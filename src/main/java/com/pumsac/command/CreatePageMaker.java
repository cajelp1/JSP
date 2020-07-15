package com.pumsac.command;

import javax.servlet.http.HttpServletRequest;

public class CreatePageMaker {

	public static PageMaker make(HttpServletRequest request) throws Exception{
		
		PageMaker pageMaker = new PageMaker();

		int page = Integer.parseInt(request.getParameter("page"));
		int perPageNum = Integer.parseInt(request.getParameter("perPageNum"));
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		
		
		SearchCriteria cri = new SearchCriteria();
		cri.setKeyword(keyword);
		cri.setPage(page);
		cri.setPerPageNum(perPageNum);
		cri.setSearchType(searchType);
		
		pageMaker.setCri(cri);
		request.setAttribute("cri", cri);
		return pageMaker;
	}
}
