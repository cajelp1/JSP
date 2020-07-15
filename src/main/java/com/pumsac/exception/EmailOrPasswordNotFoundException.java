package com.pumsac.exception;

public class EmailOrPasswordNotFoundException extends Exception{
	
	public EmailOrPasswordNotFoundException() {
		super("이메일 혹은 패스워드가 일치하지 않습니다");
	}
	
}
