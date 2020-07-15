package com.pumsac.command;

public class RemoveReplyCommand {
	
	private int pbno;
	private int no;
	private int replyPage;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getReplyPage() {
		return replyPage;
	}
	public void setReplyPage(int replyPage) {
		this.replyPage = replyPage;
	}
	public int getPbno() {
		return pbno;
	}
	public void setPbno(int pbno) {
		this.pbno = pbno;
	}
	
}
