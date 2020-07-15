package com.pumsac.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class BoardVO {

	private String boardName;
	private int sno;
	private String title;
	private String content;
	private String writer;
	private int viewcnt;
	private Date regDate;
	private Date updateDate;
	private Date startDate;
	private Date endDate;
	private String deleteyn;
	private String fileopenyn;
	private String writerNickName;

	public BoardVO() {
	}

	public BoardVO(String boardName, int sno, String title, String content, String writer, int viewcnt, Date regDate,
			Date updateDate, Date startDate, Date endDate, String deleteyn, String fileopenyn, String writerNickName) {
		super();
		this.boardName = boardName;
		this.sno = sno;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.viewcnt = viewcnt;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.deleteyn = deleteyn;
		this.fileopenyn = fileopenyn;
		this.writerNickName = writerNickName;
	}



	public String getWriterNickName() {
		return writerNickName;
	}

	public void setWriterNickName(String writerNickName) {
		this.writerNickName = writerNickName;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	public Date getRegDate() {
		return regDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getStartDate() {
		return startDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getEndDate() {
		return endDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDeleteyn() {
		return deleteyn;
	}

	public void setDeleteyn(String deleteyn) {
		this.deleteyn = deleteyn;
	}

	public String getFileopenyn() {
		return fileopenyn;
	}

	public void setFileopenyn(String fileopenyn) {
		this.fileopenyn = fileopenyn;
	}

	@Override
	public String toString() {
		return "BoardVO{" +
				"boardName='" + boardName + '\'' +
				", sno=" + sno +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", writer='" + writer + '\'' +
				", viewcnt=" + viewcnt +
				", regDate=" + regDate +
				", updateDate=" + updateDate +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", deleteyn='" + deleteyn + '\'' +
				", fileopenyn='" + fileopenyn + '\'' +
				", writerNickName='" + writerNickName + '\'' +
				'}';
	}
}
