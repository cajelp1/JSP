package com.spring.exception;

public class MultipartFileEmptyException extends Exception {
	
	public MultipartFileEmptyException() {
		super("첨부파일이 누락되었습니다");
	}

}
