package com.pumsac.vo;

import java.util.Date;

public class ReplyVO {
	
	private int no;
	private int pbno;
	private int sno;
	private String content;
	private String writer;
	private Date regdate;
	private String deleteyn;
	private Date updateDate;
	
	private String name;
	private String nickname;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getPbno() {
		return pbno;
	}
	public void setPbno(int pbno) {
		this.pbno = pbno;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getDeleteyn() {
		return deleteyn;
	}
	public void setDeleteyn(String deleteyn) {
		this.deleteyn = deleteyn;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "ReplyVO [no=" + no + ", pbno=" + pbno + ", sno=" + sno + ", content=" + content + ", writer=" + writer
				+ ", regdate=" + regdate + ", deleteyn=" + deleteyn + ", updateDate=" + updateDate + ", name=" + name
				+ ", nickname=" + nickname + "]";
	}
	
	
	
}
