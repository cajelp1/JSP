package com.pumsac.vo;

import java.util.Date;

public class ProBoardMasterVO {
	private int projectId;
	private String boardName;
	private String boardType;
	private String fileYn;
	private String replyYn;
	private String fileDownloadYn;
	private String register;
	private Date regDate;
	
	
	public ProBoardMasterVO() { }

	public ProBoardMasterVO(int projectId, String boardName, String boardType, String fileYn, String replyYn,
			String fileDownloadYn, String register, Date regDate) {
		super();
		this.projectId = projectId;
		this.boardName = boardName;
		this.boardType = boardType;
		this.fileYn = fileYn;
		this.replyYn = replyYn;
		this.fileDownloadYn = fileDownloadYn;
		this.register = register;
		this.regDate = regDate;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getBoardType() {
		return boardType;
	}

	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}

	public String getFileYn() {
		return fileYn;
	}

	public void setFileYn(String fileYn) {
		this.fileYn = fileYn;
	}

	public String getReplyYn() {
		return replyYn;
	}

	public void setReplyYn(String replyYn) {
		this.replyYn = replyYn;
	}

	public String getFileDownloadYn() {
		return fileDownloadYn;
	}

	public void setFileDownloadYn(String fileDownloadYn) {
		this.fileDownloadYn = fileDownloadYn;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "ProBoardMasterVO{" +
				"projectId=" + projectId +
				", boardName='" + boardName + '\'' +
				", boardType='" + boardType + '\'' +
				", fileYn='" + fileYn + '\'' +
				", replyYn='" + replyYn + '\'' +
				", fileDownloadYn='" + fileDownloadYn + '\'' +
				", register='" + register + '\'' +
				", regDate=" + regDate +
				'}';
	}
}
