package com.pumsac.vo;

import java.util.Date;

public class MemberVO {

	private String email;
	private String password;
	private String phoneNumber;
	private String name;
	private String nickname;
	private String postCode;
	private String address;
	private String detailAddress;
	private String picture;
	private String role;
	private String deleteyn;
	private Date regdate;

	public MemberVO() {
	}

	public MemberVO(String email, String password, String phoneNumber, String name, String nickname, String postCode,
			String address, String detailAddress, String picture, String role, String deleteyn, Date regdate) {
		super();
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.nickname = nickname;
		this.postCode = postCode;
		this.address = address;
		this.detailAddress = detailAddress;
		this.picture = picture;
		this.role = role;
		this.deleteyn = deleteyn;
		this.regdate = regdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDeleteyn() {
		return deleteyn;
	}

	public void setDeleteyn(String deleteyn) {
		this.deleteyn = deleteyn;
	}

//	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	public Date getRegdate() {
		return regdate;
	}

//	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "MemberVO [email=" + email + ", password=" + password + ", phoneNumber=" + phoneNumber + ", name=" + name
				+ ", nickname=" + nickname + ", postCode=" + postCode + ", address=" + address + ", detailAddress="
				+ detailAddress + ", picture=" + picture + ", role=" + role + ", deleteyn=" + deleteyn + ", regdate="
				+ regdate + ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword() + ", getPhoneNumber()="
				+ getPhoneNumber() + ", getName()=" + getName() + ", getNickname()=" + getNickname()
				+ ", getPostCode()=" + getPostCode() + ", getAddress()=" + getAddress() + ", getDetailAddress()="
				+ getDetailAddress() + ", getPicture()=" + getPicture() + ", getRole()=" + getRole()
				+ ", getDeleteyn()=" + getDeleteyn() + ", getRegdate()=" + getRegdate() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
