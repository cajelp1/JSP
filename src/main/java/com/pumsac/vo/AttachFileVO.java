package com.pumsac.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AttachFileVO {

	private int no;
	private int pno;
	private String attachDirectory;
	private int sno;
	private int mailNo;
	private int perSchNo;
	private String id;
	private String storedFilename;
	private String originFilename;
	private String extension;
	private int fileSize;
	private Date regdate;
	private String email;

	public AttachFileVO() {	}

	public AttachFileVO(int no, int pno, String attachDirectory, int sno, int mailNo, int perSchNo, String id,
			String storedFilename, String originFilename, String extension, int fileSize, Date regdate, String email) {
		super();
		this.no = no;
		this.pno = pno;
		this.attachDirectory = attachDirectory;
		this.sno = sno;
		this.mailNo = mailNo;
		this.perSchNo = perSchNo;
		this.id = id;
		this.storedFilename = storedFilename;
		this.originFilename = originFilename;
		this.extension = extension;
		this.fileSize = fileSize;
		this.regdate = regdate;
		this.email = email;
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getAttachDirectory() {
		return attachDirectory;
	}

	public void setAttachDirectory(String attachDirectory) {
		this.attachDirectory = attachDirectory;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getMailNo() {
		return mailNo;
	}

	public void setMailNo(int mailNo) {
		this.mailNo = mailNo;
	}

	public int getPerSchNo() {
		return perSchNo;
	}

	public void setPerSchNo(int perSchNo) {
		this.perSchNo = perSchNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStoredFilename() {
		return storedFilename;
	}

	public void setStoredFilename(String storedFilename) {
		this.storedFilename = storedFilename;
	}

	public String getOriginFilename() {
		return originFilename;
	}

	public void setOriginFilename(String originFilename) {
		this.originFilename = originFilename;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	public Date getRegdate() {
		return regdate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "AttachFileVO [no=" + no + ", pno=" + pno + ", attachDirectory=" + attachDirectory + ", sno=" + sno
				+ ", mailNo=" + mailNo + ", perSchNo=" + perSchNo + ", id=" + id + ", storedFilename=" + storedFilename
				+ ", originFilename=" + originFilename + ", extension=" + extension + ", fileSize=" + fileSize
				+ ", regdate=" + regdate + ", email=" + email + "]";
	}

	

}
