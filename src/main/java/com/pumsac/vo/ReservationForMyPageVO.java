package com.pumsac.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ReservationForMyPageVO {

	private String register; // 예약한 사람
	private int no; // 예약 번호
	private int amount; // 예약 금액
	private int meetingNo; // 사무실 번호
	private Date startDate; // 사용 시작일
	private Date endDate; // 사용 종료일
	private String refund; // 환불 여부
	private Date regDate; // 예약일
	private String roomName; // 사무실 이름
	private int capacity; // 수용 인원
	private String latitude; // 위도
	private String longitude; // 경도
	private String address; // 주소
	private String detailAddress; // 상세주소
	private int jusono; // 주소번호
	private int pricePerDay; // 하루 대여료
	private String fileName; // 프로필파일 이름
	private String uploadpath; // 저장경로

	public ReservationForMyPageVO() {	}

	public ReservationForMyPageVO(String register, int no, int amount, int meetingNo, Date startDate, Date endDate,
			String refund, Date regDate, String roomName, int capacity, String latitude, String longitude, String address,
			String detailAddress, int jusono, int pricePerDay, String fileName, String uploadpath) {
		super();
		this.register = register;
		this.no = no;
		this.amount = amount;
		this.meetingNo = meetingNo;
		this.startDate = startDate;
		this.endDate = endDate;
		this.refund = refund;
		this.regDate = regDate;
		this.roomName = roomName;
		this.capacity = capacity;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.detailAddress = detailAddress;
		this.jusono = jusono;
		this.pricePerDay = pricePerDay;
		this.fileName = fileName;
		this.uploadpath = uploadpath;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getMeetingNo() {
		return meetingNo;
	}

	public void setMeetingNo(int meetingNo) {
		this.meetingNo = meetingNo;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getStartDate() {
		return startDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getEndDate() {
		return endDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRefund() {
		return refund;
	}

	public void setRefund(String refund) {
		this.refund = refund;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	public Date getRegDate() {
		return regDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
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

	public int getJusono() {
		return jusono;
	}

	public void setJusono(int jusono) {
		this.jusono = jusono;
	}

	public int getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUploadpath() {
		return uploadpath;
	}

	public void setUploadpath(String uploadpath) {
		this.uploadpath = uploadpath;
	}

	@Override
	public String toString() {
		return "ReservationForMyPageVO{" +
				"register='" + register + '\'' +
				", no=" + no +
				", amount=" + amount +
				", meetingNo=" + meetingNo +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", refund='" + refund + '\'' +
				", regDate=" + regDate +
				", roomName='" + roomName + '\'' +
				", capacity=" + capacity +
				", latitude='" + latitude + '\'' +
				", longitude='" + longitude + '\'' +
				", address='" + address + '\'' +
				", detailAddress='" + detailAddress + '\'' +
				", jusono=" + jusono +
				", pricePerDay=" + pricePerDay +
				", fileName='" + fileName + '\'' +
				", uploadpath='" + uploadpath + '\'' +
				'}';
	}
}
