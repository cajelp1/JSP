package com.spring.request;

import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.PdsVO;

public class RegistPdsRequest {
	
	private String title;
	private String content;
	private String writer;
	private MultipartFile[] uploadFile;
	
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
	public MultipartFile[] getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile[] uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	public PdsVO toPdsVO() {
		PdsVO pds = new PdsVO();
		pds.setContent(content);
		pds.setTitle(title);
		pds.setWriter(writer);
		
		return pds;
	}
	
	
}
