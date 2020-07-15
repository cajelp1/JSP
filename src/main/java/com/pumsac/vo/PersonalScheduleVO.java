package com.pumsac.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PersonalScheduleVO {
	private int no;
	private int projectId;
	private String title;
	private String content;
	private String memberId;
	private Date startDate;
	private Date endDate;
	private Date regdate;
	private String color;
	private String charger;	// 시킨사람
	private int processPercent;	// 진척률
	private String approvalYn; 	// 최종승인 여부
	private String processStaues;	// 프로세스 상태
	private String reason;
	private Date updateDate;
	
	private String memberName;
	private String memberNickName;
	private String chargerName;	
	private String chargerNickName;
	private double avg;
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getMemberNickName() {
		return memberNickName;
	}
	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
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
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Date getStartDate() {
		return startDate;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getUpdateDate() {
		return updateDate;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCharger() {
		return charger;
	}
	public void setCharger(String charger) {
		this.charger = charger;
	}
	public int getProcessPercent() {
		return processPercent;
	}
	public void setProcessPercent(int processPercent) {
		this.processPercent = processPercent;
	}
	public String getApprovalYn() {
		return approvalYn;
	}
	public void setApprovalYn(String approvalYn) {
		this.approvalYn = approvalYn;
	}
	public String getProcessStaues() {
		return processStaues;
	}
	public void setProcessStaues(String processStaues) {
		this.processStaues = processStaues;
	}
	public String getChargerName() {
		return chargerName;
	}
	public void setChargerName(String chargerName) {
		this.chargerName = chargerName;
	}
	public String getChargerNickName() {
		return chargerNickName;
	}
	public void setChargerNickName(String chargerNickName) {
		this.chargerNickName = chargerNickName;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	@Override
	public String toString() {
		return "PersonalScheduleVO [no=" + no + ", projectId=" + projectId + ", title=" + title + ", content=" + content
				+ ", memberId=" + memberId + ", startDate=" + startDate + ", endDate=" + endDate + ", regdate="
				+ regdate + ", color=" + color + ", charger=" + charger + ", processPercent=" + processPercent
				+ ", approvalYn=" + approvalYn + ", processStaues=" + processStaues + ", reason=" + reason
				+ ", updateDate=" + updateDate + ", memberName=" + memberName + ", memberNickName=" + memberNickName
				+ ", chargerName=" + chargerName + ", chargerNickName=" + chargerNickName + ", avg=" + avg + "]";
	}
	
	
}
