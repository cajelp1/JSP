package com.pumsac.vo;

import java.util.Date;

public class SetSensorVO {
	private int roomNo;
	private int temperatured;
	private int humidity;
	private int distance;
	private int discomfortIndex;
	private String roomName;
	private Date regdate;
	
	public SetSensorVO() {}
	public SetSensorVO(int roomNo, int temperatured, int humidity, int distance, int discomfortIndex, String roomName,
			Date regdate) {
		super();
		this.roomNo = roomNo;
		this.temperatured = temperatured;
		this.humidity = humidity;
		this.distance = distance;
		this.discomfortIndex = discomfortIndex;
		this.roomName = roomName;
		this.regdate = regdate;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public int getTemperatured() {
		return temperatured;
	}
	public void setTemperatured(int temperatured) {
		this.temperatured = temperatured;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getDiscomfortIndex() {
		return discomfortIndex;
	}
	public void setDiscomfortIndex(int discomfortIndex) {
		this.discomfortIndex = discomfortIndex;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "SetSensorVO{" +
				"roomNo=" + roomNo +
				", temperatured=" + temperatured +
				", humidity=" + humidity +
				", distance=" + distance +
				", discomfortIndex=" + discomfortIndex +
				", roomName='" + roomName + '\'' +
				", regdate=" + regdate +
				'}';
	}
}
