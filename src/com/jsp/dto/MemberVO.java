package com.jsp.dto;

public class MemberVO {
	
	private String id        ;
	private String pwd       ;
	private String name      ;
	private String phone     ;
	private String email     ;
	private String address   ;
	private String picture   ;
	private String authority ;
	private Integer enabled  ;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
	
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", phone=" + phone + ", email=" + email
				+ ", address=" + address + ", picture=" + picture + ", authority=" + authority + ", enabled=" + enabled
				+ "]";
	}
	public MemberVO(String id, String pwd, String name, String phone, String email, String address, String picture,
			String authority, int enabled) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.picture = picture;
		this.authority = authority;
		this.enabled = enabled;
	}
	
	public MemberVO() {}
	
}
