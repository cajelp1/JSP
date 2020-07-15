package com.pumsac.command;

public class CriteriaRoomList {
	
	private int page; // 현재 페이지 번호
	private int perPageNum; // 한 페이지당 보여줄 행 개수
	private String city;
	private String area;
	private String capacity;
	
	public CriteriaRoomList() {
		this.page = 1;
		this.perPageNum = 6;
		this.city="";
		this.area="";
		this.capacity="";
	}
	
	public CriteriaRoomList(int page, int perPageNum, String city, String area, String capacity) {
		super();
		this.page = page;
		this.perPageNum = perPageNum;
		this.city = city;
		this.area = area;
		this.capacity = capacity;
	}
	
	public int getPageStartRowNum(){ //각 페이지마다 시작하는 행번호
		return (this.page-1)*perPageNum;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
}


