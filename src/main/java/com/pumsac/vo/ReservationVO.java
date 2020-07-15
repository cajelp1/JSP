package com.pumsac.vo;

import java.util.Date;

public class ReservationVO {
	
	private int no;
	private int amount;
	private int meetingNo;
	private Date startDate;
	private Date endDate;
	private String processtatus;
	private String projectId;
	private String register;
	private Date regdate;
	
	public ReservationVO() {	}

	public ReservationVO(int no, int amount, int meetingNo, Date startDate, Date endDate, String processtatus,
			String projectId, String register, Date regdate) {
		super();
		this.no = no;
		this.amount = amount;
		this.meetingNo = meetingNo;
		this.startDate = startDate;
		this.endDate = endDate;
		this.processtatus = processtatus;
		this.projectId = projectId;
		this.register = register;
		this.regdate = regdate;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getProcesstatus() {
		return processtatus;
	}

	public void setProcesstatus(String processtatus) {
		this.processtatus = processtatus;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "ReservationVO{" +
				"no=" + no +
				", amount=" + amount +
				", meetingNo=" + meetingNo +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", processtatus='" + processtatus + '\'' +
				", projectId='" + projectId + '\'' +
				", register='" + register + '\'' +
				", regdate=" + regdate +
				'}';
	}
}