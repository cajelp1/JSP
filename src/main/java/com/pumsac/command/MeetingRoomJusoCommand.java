package com.pumsac.command;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pumsac.vo.MeetingRoomAttachFileVO;
import com.pumsac.vo.MeetingRoomVO;

public class MeetingRoomJusoCommand {

	private int roomNo;
	private String roomName;
	private int capacity;
	private int pricePerDay;
	private String latitude;
	private String longitude;
	private String postCode;
	private String address;
	private String detailAddress;
	private Date regdate;
	private String register;
	private int jusoNo;
	private String deleteyn;

	private int realJusoNo;
	private String city;
	private String area;
	private String detail;

	// profile img
	private MultipartFile profileImg;
	private MeetingRoomAttachFileVO profile;

	// detail img
	private List<MultipartFile> picture;
	private List<MeetingRoomAttachFileVO> attachFileList;

	public MeetingRoomAttachFileVO getProfile() {
		return profile;
	}

	public void setProfile(MeetingRoomAttachFileVO profile) {
		this.profile = profile;
	}

	public MultipartFile getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(MultipartFile profileImg) {
		this.profileImg = profileImg;
	}

	public List<MultipartFile> getPicture() {
		return picture;
	}

	public void setPicture(List<MultipartFile> picture) {
		this.picture = picture;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public int getJusoNo() {
		return jusoNo;
	}

	public void setJusoNo(int jusoNo) {
		this.jusoNo = jusoNo;
	}

	public int getRealJusoNo() {
		return realJusoNo;
	}

	public void setRealJusoNo(int realJusoNo) {
		this.realJusoNo = realJusoNo;
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

	public int getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public String getDeleteyn() {
		return deleteyn;
	}

	public void setDeleteyn(String deleteyn) {
		this.deleteyn = deleteyn;
	}

	public List<MeetingRoomAttachFileVO> getAttachFileList() {
		return attachFileList;
	}

	public void setAttachFileList(List<MeetingRoomAttachFileVO> attachFileList) {
		this.attachFileList = attachFileList;
	}

	public MeetingRoomVO toMeetingRoomVO() {
		MeetingRoomVO room = new MeetingRoomVO();

		room.setNo(roomNo);
		room.setRoomName(roomName);
		room.setCapacity(capacity);
		room.setPricePerDay(pricePerDay);
		room.setAddress(address);
		room.setDetailAddress(detailAddress);
		room.setLatitude(latitude);
		room.setLongitude(longitude);
		room.setJusoNo(jusoNo);
		room.setRegister(register);

		return room;
	}

}
