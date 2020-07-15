package com.pumsac.vo;

public class OffScheduleDetailVO {
	private int no;
	private String address;
	private String detailAddress;
	private float latitude;
	private float longtitude;
	private int alarmTime;
	
	
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
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
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(float longtitude) {
		this.longtitude = longtitude;
	}
	public int getAlarmTime() {
		return alarmTime;
	}
	public void setAlarmTime(int alarmTime) {
		this.alarmTime = alarmTime;
	}

	@Override
	public String toString() {
		return "OffScheduleDetailVO{" +
				"no=" + no +
				", address='" + address + '\'' +
				", detailAddress='" + detailAddress + '\'' +
				", latitude=" + latitude +
				", longtitude=" + longtitude +
				", alarmTime=" + alarmTime +
				'}';
	}
}
