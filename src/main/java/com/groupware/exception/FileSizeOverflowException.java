package com.groupware.exception;

public class FileSizeOverflowException extends Exception {
	
	public  FileSizeOverflowException() {
		super("파일용량이 초과되었습니다");
	}
}
