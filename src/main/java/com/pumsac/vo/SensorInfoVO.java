package com.pumsac.vo;

import java.util.Date;

public class SensorInfoVO {
	private int no;
	private int type;
	private int val;
	private int roomNo;
	private String reason;
	private String readYn;
	private String roomName;
	private Date checkDate;
	private Date regDate;
	
	public SensorInfoVO() { }

	public SensorInfoVO(int no, int type, int val, int roomNo, String reason, String readYn, String roomName,
			Date checkDate, Date regDate) {
		super();
		this.no = no;
		this.type = type;
		this.val = val;
		this.roomNo = roomNo;
		this.reason = reason;
		this.readYn = readYn;
		this.roomName = roomName;
		this.checkDate = checkDate;
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReadYn() {
		return readYn;
	}

	public void setReadYn(String readYn) {
		this.readYn = readYn;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "SensorInfoVO{" +
				"no=" + no +
				", type=" + type +
				", val=" + val +
				", roomNo=" + roomNo +
				", reason='" + reason + '\'' +
				", readYn='" + readYn + '\'' +
				", roomName='" + roomName + '\'' +
				", checkDate=" + checkDate +
				", regDate=" + regDate +
				'}';
	}
}
