package com.pumsac.vo;

import java.util.Date;

public class NotHistoryVO {
	private int no;
	private int projectId;
	private String title;
	private String content;
	private String sender;
	private String receiver;
	private String url;
	private int type;
	private String readyn;
	private Date regDate;
	private String senderName;
	private String senderNickName;
	
	public NotHistoryVO() { }

	public NotHistoryVO(int no, int projectId, String title, String content, String sender, String receiver,
			String url, int type, String readyn, Date regDate, String senderName, String senderNickName) {
		super();
		this.no = no;
		this.projectId = projectId;
		this.title = title;
		this.content = content;
		this.sender = sender;
		this.receiver = receiver;
		this.url = url;
		this.type = type;
		this.readyn = readyn;
		this.regDate = regDate;
		this.senderName = senderName;
		this.senderNickName = senderNickName;
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

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getReadyn() {
		return readyn;
	}

	public void setReadyn(String readyn) {
		this.readyn = readyn;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderNickName() {
		return senderNickName;
	}

	public void setSenderNickName(String senderNickName) {
		this.senderNickName = senderNickName;
	}

	@Override
	public String toString() {
		return "NotHistoryVO{" +
				"no=" + no +
				", projectId=" + projectId +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", sender='" + sender + '\'' +
				", receiver='" + receiver + '\'' +
				", url='" + url + '\'' +
				", type=" + type +
				", readyn='" + readyn + '\'' +
				", regDate=" + regDate +
				", senderName='" + senderName + '\'' +
				", senderNickName='" + senderNickName + '\'' +
				'}';
	}
}
