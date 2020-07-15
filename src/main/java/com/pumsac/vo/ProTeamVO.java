package com.pumsac.vo;

public class ProTeamVO {
	private int projectId;
	private String memberId;
	private String job;
	private String regDate;
	
	private String memberName;
	private String memberNickName;
	private String positionName;
	private String projectName;
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int no) {
		this.projectId = no;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
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
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	@Override
	public String toString() {
		return "ProTeamVO{" +
				"projectId=" + projectId +
				", memberId='" + memberId + '\'' +
				", job='" + job + '\'' +
				", regDate='" + regDate + '\'' +
				", memberName='" + memberName + '\'' +
				", memberNickName='" + memberNickName + '\'' +
				", positionName='" + positionName + '\'' +
				", projectName='" + projectName + '\'' +
				'}';
	}
}
