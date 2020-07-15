package com.pumsac.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pumsac.service.AttachFileService;
import com.pumsac.vo.AttachFileVO;

@Controller
public class DownloadFile {

	@Autowired
	private AttachFileService attachService;
	
	@Resource(name="siteNoticeBoard")
	private String noticeAttach;
	
//	@RequestMapping(value="/download/{sno}")
//	public void noticeAttachDownload(@PathVariable("sno") int sno) throws SQLException{
//		AttachFileVO attach =  attachService.getAttachFileBySno(sno);
//	
//		//서버 저장 경로
//		String savePath = noticeAttach;
//		
//		//원본파일 이름
//		String originalFileName = attach.getOriginFilename();
//		
//		FileInputStream fis = null;
//		BufferedInputStream bis = null;
//		ServletOutputStream sos = null;
//		
//		
//
//	}
}
