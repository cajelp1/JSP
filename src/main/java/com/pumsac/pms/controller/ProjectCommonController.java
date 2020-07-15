package com.pumsac.pms.controller;

import com.pumsac.pms.service.ProjectCommonService;
import com.pumsac.vo.AttachFileVO;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.ProMasterVO;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;

@Controller
@RequestMapping("/pms/project/*")
public class ProjectCommonController {

	@Autowired
	private ProjectCommonService projectCommonService;
	
	@RequestMapping(value="regist", method=RequestMethod.GET)
	public ModelAndView getRegistProject() {
		ModelAndView mnv = new ModelAndView();
		String url = "/commons/project/regist";
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping(value="regist", method=RequestMethod.POST)
	public ModelAndView PostregistProject(ProMasterVO pro, HttpSession session, ModelAndView mnv) {
		String url = "/commons/project/regist_success";
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		pro.setRegister(loginUser.getEmail());
		try {
			projectCommonService.registProject(pro);			
		} catch (SQLException e) {
			url = "error/500_error";
		}
		
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping(value="downloadFile")
	@ResponseBody
	public ResponseEntity<byte[]> downloadFile(int no) throws Exception {
		
		AttachFileVO fileInfo = projectCommonService.getFileInfo(no);
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		HttpHeaders headers = new HttpHeaders();
		String savePath = fileInfo.getAttachDirectory();
		String fileName =  fileInfo.getStoredFilename();
		
		try {
			in = new FileInputStream(savePath + File.separator + fileName);
			fileName = fileInfo.getOriginFilename();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment;filename=\"" + new String(fileName.getBytes("utf-8"), "ISO-8859-1") + "\"");
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			in.close();
		}
		return entity;
	}
}
