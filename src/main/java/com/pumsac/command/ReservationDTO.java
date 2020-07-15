package com.pumsac.command;

import java.util.Date;

import com.pumsac.vo.ReservationVO;

public class ReservationDTO {
	
	private int no;
	private int amount;
	private int meetingNo;
	
	private String meetingName;
	
	private Date startDate;
	private Date endDate;
	private String processtatus;
	private String projectId;
	
	private String projectName;
	
	private Date regdate;
	private String register;
	
	private String registerName;
	private String registerNickname;

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

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public String getRegisterName() {
		return registerName;
	}

	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}

	public String getRegisterNickname() {
		return registerNickname;
	}

	public void setRegisterNickname(String registerNickname) {
		this.registerNickname = registerNickname;
	}

	public ReservationVO getReservationVO() {
		ReservationVO reservation = new ReservationVO();
		
		reservation.setNo(no);
		reservation.setAmount(amount);
		reservation.setMeetingNo(meetingNo);
		reservation.setStartDate(startDate);
		reservation.setEndDate(endDate);
		reservation.setProcesstatus(processtatus);
		reservation.setProjectId(projectId);
		reservation.setRegister(register);
		reservation.setRegdate(regdate);
		
		return reservation;
	}
	
}
