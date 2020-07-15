package com.pumsac.command;

import com.pumsac.vo.AttachFileVO;
import com.pumsac.vo.MemberVO;
import org.springframework.web.multipart.MultipartFile;

public class RegistMemberCommand {

	private String email;
	private String password;
	private String phoneNumber;
	private String name;
	private String nickname;
	private String postCode;
	private String address;
	private String detailAddress;
	private MultipartFile imageFileUpload;
	private String picture;
	private String role;

	public RegistMemberCommand() {	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public MultipartFile getImageFileUpload() {
		return imageFileUpload;
	}

	public void setImageFileUpload(MultipartFile imageFileUpload) {
		this.imageFileUpload = imageFileUpload;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public MemberVO toMemberVO() {
		MemberVO member = new MemberVO();
		member.setAddress(address);
		member.setDetailAddress(detailAddress);
		member.setEmail(email);
		member.setName(name);
		member.setNickname(nickname);
		member.setPassword(password);
		member.setPhoneNumber(phoneNumber);
		member.setPostCode(postCode);

		return member;
	}

	/**
	 * 프로필 이미지 등록하기 위해 만든 VO
	 * 
	 * @return AttachFileVO
	 */
	public AttachFileVO toAttachFileVO() {

		AttachFileVO attach = new AttachFileVO();
		if(picture != "") {
			String fileName = picture;
			String originFilename = picture.substring(picture.lastIndexOf("$$") + 2);
			attach.setFileSize(0);
			attach.setOriginFilename(originFilename);
			attach.setExtension(originFilename.substring(originFilename.lastIndexOf(".") + 1));
			attach.setStoredFilename(fileName);
			attach.setId(email);
			attach.setEmail(email);			
		}

		return attach;
	}
}
