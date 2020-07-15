package com.pumsac.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class OfficialScheduleVO {
	private int no;
	private int projectId;
	private String title;
	private String content;
	private String register;
	private String registerName;
	private String registerNickName;
	private Date regDate;
	private Date startDate;
	private String color;
	private Date endDate;

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

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
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

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRegisterName() {
		return registerName;
	}

	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}

	public String getRegisterNickName() {
		return registerNickName;
	}

	public void setRegisterNickName(String registerNickName) {
		this.registerNickName = registerNickName;
	}

	@Override
	public String toString() {
		return "OfficialScheduleVO{" +
				"no=" + no +
				", projectId=" + projectId +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", register='" + register + '\'' +
				", registerName='" + registerName + '\'' +
				", registerNickName='" + registerNickName + '\'' +
				", regDate=" + regDate +
				", startDate=" + startDate +
				", color='" + color + '\'' +
				", endDate=" + endDate +
				'}';
	}
}
