package com.jsp.request;

import com.jsp.dto.MemberVO;

public class LoginRequest{
	
	private String id;
	private String pwd;
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getPwd(){
		return pwd;
	}
	public void setPwd(String pwd){
		this.pwd = pwd;
	}
	
	public MemberVO toMemberVO() {
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPwd(pwd);
		
		return member;
	}
}
