package com.pumsac.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class ProBoardVO {
	private String boardName;
    private int  no;
	private String title;
	private String content;
	private String writer;
	private String wrtierNickName;
	private String viewCnt;
	private Date regDate;
	private Date updateDate;
	private Date startDate;
	private Date endDate;
	private String deleteYn;
	private String fileOpenYn;
	private List<AttachFileVO> attachList;

	public ProBoardVO() { }
	public ProBoardVO(String boardName, int no, String title, String content, String writer, String wrtierNickName,
			String viewCnt, Date regDate, Date updateDate, Date startDate, Date endDate, String deleteYn,
			String fileOpenYn, List<AttachFileVO> attachList) {
		super();
		this.boardName = boardName;
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.wrtierNickName = wrtierNickName;
		this.viewCnt = viewCnt;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.deleteYn = deleteYn;
		this.fileOpenYn = fileOpenYn;
		this.attachList = attachList;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public String getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(String viewCnt) {
		this.viewCnt = viewCnt;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getStartDate() {
		return startDate;
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

	public String getDeleteYn() {
		return deleteYn;
	}

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}

	public String getFileOpenYn() {
		return fileOpenYn;
	}

	public void setFileOpenYn(String fileOpenYn) {
		this.fileOpenYn = fileOpenYn;
	}

	public List<AttachFileVO> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<AttachFileVO> attachList) {
		this.attachList = attachList;
	}

	public String getWrtierNickName() {
		return wrtierNickName;
	}

	public void setWrtierNickName(String wrtierNickName) {
		this.wrtierNickName = wrtierNickName;
	}

	@Override
	public String toString() {
		return "ProBoardVO{" +
				"boardName='" + boardName + '\'' +
				", no=" + no +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", writer='" + writer + '\'' +
				", wrtierNickName='" + wrtierNickName + '\'' +
				", viewCnt='" + viewCnt + '\'' +
				", regDate=" + regDate +
				", updateDate=" + updateDate +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", deleteYn='" + deleteYn + '\'' +
				", fileOpenYn='" + fileOpenYn + '\'' +
				", attachList=" + attachList +
				'}';
	}
}
