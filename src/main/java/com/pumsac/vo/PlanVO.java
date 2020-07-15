package com.pumsac.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PlanVO {
	private int no;
	private String title;
	private String content;
	private int projectId;
	private String memberId;
	private Date startDate;
	private Date endDate;
	private String color;
	private String code;
	private Date regDate;
	private Date updateDate;
	private String address;
	private String detailAddress;
	private float latitude;
	private float longitude;
	private String fromTime;
	private String toTime;
	private String memberName;
	private String memberNickName;

	public PlanVO() {	}
	public PlanVO(int no, String title, String content, int projectId, String memberId, Date startDate, Date endDate,
			String color, String code, Date regDate, Date updateDate, String address, String detailAddress,
			float latitude, float longitude, String fromTime, String toTime, String memberName, String memberNickName) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.projectId = projectId;
		this.memberId = memberId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.color = color;
		this.code = code;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.address = address;
		this.detailAddress = detailAddress;
		this.latitude = latitude;
		this.longitude = longitude;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.memberName = memberName;
		this.memberNickName = memberNickName;
	}


	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberNickName() {
		return memberNickName;
	}
	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}
	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "PlanVO [no=" + no + ", title=" + title + ", content=" + content + ", projectId=" + projectId
				+ ", memberId=" + memberId + ", startDate=" + startDate + ", endDate=" + endDate + ", color=" + color
				+ ", code=" + code + ", regDate=" + regDate + ", updateDate=" + updateDate + ", address=" + address
				+ ", detailAddress=" + detailAddress + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
