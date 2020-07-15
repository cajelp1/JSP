package com.spring.request;

public class DeleteReplyRequest {
	private int bno;
	private int rno;
	private int replyPage;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getReplyPage() {
		return replyPage;
	}
	public void setReplyPage(int replyPage) {
		this.replyPage = replyPage;
	}
	
}
