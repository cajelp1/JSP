package com.pumsac.command;

public class SearchCriteria {

	private int page;
	private int perPageNum;
	private String searchType;
	private String keyword;
	private String orderByCondition;
	private String memberId;
	private int projectId;
	private int job;

	public SearchCriteria() {
		super();
		this.page = 1;
		this.perPageNum = 10;
		this.searchType = "";
		this.keyword = "";
		this.orderByCondition="";
		this.memberId="";
		this.projectId= 0;
		this.job=0;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
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

	public String getOrderByCondition() {
		return orderByCondition;
	}

	public void setOrderByCondition(String orderByCondition) {
		this.orderByCondition = orderByCondition;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getJob() {
		return job;
	}

	public void setJob(int job) {
		this.job = job;
	}
}
