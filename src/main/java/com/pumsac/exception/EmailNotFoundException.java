package com.pumsac.exception;

public class EmailNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmailNotFoundException() {
		super("Email을 찾을 수 없습니다.");		
	}
}
