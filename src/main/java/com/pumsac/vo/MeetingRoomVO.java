package com.pumsac.vo;

import java.util.Date;

public class MeetingRoomVO {
	
	private int no;
	private String roomName;
	private int capacity;
	private int pricePerDay;
	private String latitude;
	private String longitude;
	private String address;
	private String detailAddress;
	private Date regdate;
	private String register;
	private int jusoNo;
	private String deleteyn;
	
	public MeetingRoomVO() { }
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getDeleteyn() {
		return deleteyn;
	}
	public void setDeleteyn(String deleteyn) {
		this.deleteyn = deleteyn;
	}
	public int getPricePerDay() {
		return pricePerDay;
	}
	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	
	@Override
	public String toString() {
		return "MeetingRoomVO [no=" + no + ", roomName=" + roomName + ", capacity=" + capacity + ", pricePerDay="
				+ pricePerDay + ", latitude=" + latitude + ", longitude=" + longitude + ", address=" + address
				+ ", detailAddress=" + detailAddress + ", regdate=" + regdate + ", register=" + register + ", jusoNo="
				+ jusoNo + ", deleteyn=" + deleteyn + "]";
	}

	public MeetingRoomVO(int no, String roomName, int capacity, int pricePerDay, String latitude, String longitude,
			String address, String detailAddress, Date regdate, String register, int jusoNo, String deleteyn) {
		super();
		this.no = no;
		this.roomName = roomName;
		this.capacity = capacity;
		this.pricePerDay = pricePerDay;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.detailAddress = detailAddress;
		this.regdate = regdate;
		this.register = register;
		this.jusoNo = jusoNo;
		this.deleteyn = deleteyn;
	}

	
}
