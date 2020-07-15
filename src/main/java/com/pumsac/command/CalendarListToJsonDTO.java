package com.pumsac.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CalendarListToJsonDTO {
	private int id;
	private String title;
	private Date start;
	private Date end;
	private String backgroundColor;
	
	public CalendarListToJsonDTO() {}
	public CalendarListToJsonDTO(int id, String title, Date start, Date end, String backgroundColor) {
		super();
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
		this.backgroundColor = backgroundColor;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStart() {
		return start;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	

	
}
