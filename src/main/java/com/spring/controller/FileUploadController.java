package com.spring.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.exception.FileSizeOverflowException;
import com.spring.exception.MultipartFileEmptyException;
import com.spring.request.FileUploadCommand;
import com.spring.util.FileUpload;

@Controller
public class FileUploadController {
	
	@RequestMapping("/fileUploadForm")
	public void form() {}
	
	
	@RequestMapping(value="/multipartFile", method=RequestMethod.POST)	//
	public String uploadByMultipartFile(String title, 
			@RequestParam("file")MultipartFile multi, Model model, 
//			HttpServletResponse response,
			HttpServletRequest request) throws IOException{
		//response를 parameter로 받으면 adaptor가 따로 주소를 찾지 않는다.
		//request는 경로가 필요해서 가져왔고 response는 script를 바로 내보내기 위해 가져옴
		
		String url = "fileUpload_success";
		
		/*
		response.setContentType("text/html);charset=utf-8");
		PrintWriter out = response.getWriter();
		File uploadfile = null;
		
		try {
			uploadfile = saveFile(multi,request);
		} catch (FileSizeOverflowException e) {
			//파일유무확인
			e.printStackTrace();
			out.println("<script>alert('용량초과입니다!')</script>");
			out.println("<script>history.go(-1);</script>");
			return null;
		} catch (MultipartFileEmptyException e) {
			//용량제한 5MB. 사실 여기서가 아니라 화면에서 유효성이 끝났어야.
			e.printStackTrace();
			out.println("<script>alert('파일이 없습니다!');</script>");
			out.println("<script>history.go(-1);</script>");
			return null;
		}finally {
			out.close();
		}
		
		model.addAttribute("title", title);
		model.addAttribute("originalFileName", multi.getOriginalFilename());
		model.addAttribute("uploadedFileName", uploadfile.getName());
		model.addAttribute("uploadPath", uploadfile.getAbsolutePath());
		*/
		
		try {
			File uploadfile = FileUpload.saveFile(multi,request);
			model.addAttribute("title", title);
			model.addAttribute("originalFileName", multi.getOriginalFilename());
			model.addAttribute("uploadedFileName", uploadfile.getName());
			model.addAttribute("uploadPath", uploadfile.getAbsolutePath());
			
		} catch (FileSizeOverflowException | MultipartFileEmptyException e) {
			e.printStackTrace();
			model.addAttribute("exception",e);
			url = "fileUpload_fail";
		}
		
		return url;
	}
	
	//코드 분리시에는 메소드 안에서 try-catch를 하면 안된다!
	//현재는 따로 클래스 분리
	
	
	@RequestMapping(value = "multipartHttpServletRequest",method=RequestMethod.POST)
	public String uploadByMultipartHttpServletRequest(MultipartHttpServletRequest request,
													Model model) throws IOException{
		String url = "fileUpload_success";
		
		String title = request.getParameter("title");
		MultipartFile multi = request.getFile("file");
		
		try {
			File uploadfile = FileUpload.saveFile(multi,request);
			model.addAttribute("title", title);
			model.addAttribute("originalFileName", multi.getOriginalFilename());
			model.addAttribute("uploadedFileName", uploadfile.getName());
			model.addAttribute("uploadPath", uploadfile.getAbsolutePath());
			
		} catch (FileSizeOverflowException | MultipartFileEmptyException e) {
			e.printStackTrace();
			model.addAttribute("exception",e);
			url = "fileUpload_fail";
		}
		
		return url;
	}
	
	
	
	@RequestMapping(value = "/commandObject",method=RequestMethod.POST)
	public String uploadByCommandObject(FileUploadCommand command, HttpServletRequest request, Model model)
			throws IOException{
		
		String url = "fileUpload_success";
		
		MultipartFile multi = command.getFile();
		String title = command.getTitle();
		
		try {
			File uploadfile = FileUpload.saveFile(multi,request);
			model.addAttribute("title", title);
			model.addAttribute("originalFileName", multi.getOriginalFilename());
			model.addAttribute("uploadedFileName", uploadfile.getName());
			model.addAttribute("uploadPath", uploadfile.getAbsolutePath());
			
		} catch (FileSizeOverflowException | MultipartFileEmptyException e) {
			e.printStackTrace();
			model.addAttribute("exception",e);
			url = "fileUpload_fail";
		}
		
		return url;
	}
	
}
