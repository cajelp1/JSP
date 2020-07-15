package com.pumsac.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class LoginLogVO {
	
	private int no;
	private String accessId;
	private String ipAddress;
	private Date loginTime;
	private Date logout;
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getAccessId() {
		return accessId;
	}
	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	public Date getLoginTime() {
		return loginTime;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	public Date getLogout() {
		return logout;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	public void setLogout(Date logout) {
		this.logout = logout;
	}
	@Override
	public String toString() {
		return "LoginLogVO [no=" + no + ", accessId=" + accessId + ", ipAddress=" + ipAddress + ", loginTime="
				+ loginTime + ", logout=" + logout + "]";
	}
	
	public LoginLogVO() { }
	
	public LoginLogVO(int no, String accessId, String ipAddress, Date loginTime, Date logout) {
		super();
		this.no = no;
		this.accessId = accessId;
		this.ipAddress = ipAddress;
		this.loginTime = loginTime;
		this.logout = logout;
	}
	
}
