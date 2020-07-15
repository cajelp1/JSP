package com.jsp.request;

import java.util.Arrays;

import com.jsp.dto.MemberVO;

public class MemberRegistRequest {
	
	//request객체는 vo에 담기지 않거나 담으려고 가공하기 전에 쓰는 객체.
	
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String[] phone;
	private String picture;
	private String authority;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String[] getPhone() {
		return phone;
	}
	public void setPhone(String[] phone) {
		this.phone = phone;
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
	
	@Override
	public String toString() {
		return "MemberRegistRequest [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", phone="
				+ Arrays.toString(phone) + ", picture=" + picture + ", authority=" + authority + "]";
	}
	
	public MemberRegistRequest() {};
	
	public MemberRegistRequest(String id, String pwd, String name, String email, String[] phone, String picture,
			String authority) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.picture = picture;
		this.authority = authority;
	}
	
	public MemberVO toMemberVO() {
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPwd(pwd);
		member.setName(name);
		member.setEmail(email);
		member.setPicture(picture);
		member.setAuthority(authority);
		member.setPhone(phone[0]+phone[1]+phone[2]);
		
		return member;
	}
	
}
