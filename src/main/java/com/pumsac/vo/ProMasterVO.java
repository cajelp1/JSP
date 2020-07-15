package com.pumsac.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class ProMasterVO {
	private int projectId;
	private String projectName;
	private String projectExplain;
	private Date startDate;
	private Date endDate;
	private Date regDate;
	private String deleteyn;
	private String register;
	private String completeYn;

	List<ProTeamVO> proTeamList;

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getStartDate() {
		return startDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getRegDate() {
		return regDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public List<ProTeamVO> getProTeamList() {
		return proTeamList;
	}

	public void setProTeamList(List<ProTeamVO> proTeamList) {
		this.proTeamList = proTeamList;
	}

	public String getDeleteyn() {
		return deleteyn;
	}

	public void setDeleteyn(String deleteyn) {
		this.deleteyn = deleteyn;
	}

	public String getProjectExplain() {
		return projectExplain;
	}

	public void setProjectExplain(String projectExplain) {
		this.projectExplain = projectExplain;
	}

	public String getCompleteYn() {
		return completeYn;
	}

	public void setCompleteYn(String completeYn) {
		this.completeYn = completeYn;
	}

	@Override
	public String toString() {
		return "ProMasterVO{" +
				"projectId=" + projectId +
				", projectName='" + projectName + '\'' +
				", projectExplain='" + projectExplain + '\'' +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", regDate=" + regDate +
				", deleteyn='" + deleteyn + '\'' +
				", register='" + register + '\'' +
				", completeYn='" + completeYn + '\'' +
				", proTeamList=" + proTeamList +
				'}';
	}
}
