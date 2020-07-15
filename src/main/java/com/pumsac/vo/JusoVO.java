package com.pumsac.vo;

public class JusoVO {
	
	private Integer no;
	private String city;
	private String area;
	private String detail;
	
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
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
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
	@Override
	public String toString() {
		return "JusoMasterVO [no=" + no + ", city=" + city + ", area=" + area + ", detail=" + detail + "]";
	}
	public JusoVO() {	}
	public JusoVO(Integer no, String city, String area, String detail) {
		super();
		this.no = no;
		this.city = city;
		this.area = area;
		this.detail = detail;
	}

	
	
}
