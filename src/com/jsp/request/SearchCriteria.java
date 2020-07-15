package com.jsp.request;

public class SearchCriteria {

	
	private int page;
	private int perPageNum;
	private String searchType;
	private String keyword;
	
	public SearchCriteria() {
		super();
		this.page = 1;
		this.perPageNum = 10;
		this.searchType = "";
		this.keyword = "";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	
	public int getPageStartRowNum() {		//각 페이지마다 시작하는 행번호
		return (this.page-1)*perPageNum;
	}
	
}
