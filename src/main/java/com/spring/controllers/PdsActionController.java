package com.spring.controllers;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dto.AttachVO;
import com.spring.dto.MemberVO;
import com.spring.dto.PdsVO;
import com.spring.request.RegistPdsRequest;
import com.spring.request.SearchCriteria;
import com.spring.service.PdsService;

@Controller
@RequestMapping("/pds/*")
public class PdsActionController {
	
	@Autowired
	private PdsService pdsService;
	/*public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}*/
	
	@RequestMapping("/list.do")
	public ModelAndView list(SearchCriteria cri, ModelAndView mnv) throws Exception{
		String url = "pds/list.page";
		
		Map<String, Object> dataMap = pdsService.getList(cri);
		
		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/registForm.do")
	public ModelAndView registForm(ModelAndView mnv) throws Exception{
		String url = "pds/regist.open";
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping(value="regist.do", method=RequestMethod.POST, 
			produces="text/plain;charset=utf-8")
	public String regist(RegistPdsRequest registReq,
						HttpServletRequest request,
						HttpServletResponse response) throws Exception{
		String url = "pds/regist_success";
		
		List<AttachVO> attachList = saveFile(registReq, request, response);
		
		PdsVO pds = registReq.toPdsVO();
		pds.setAttachList(attachList);
		
		try {
			pdsService.regist(pds);
		}catch(SQLException e) {
			e.printStackTrace();
			url="pds/regist_fail";
		}
		
		return url;
	}
	
	
	@RequestMapping("/modifyForm.do")
	public ModelAndView modifyForm(ModelAndView mnv, int pno) throws Exception{
		String url = "pds/modify.open";
		
		PdsVO pds = pdsService.getPds(pno);
		
		mnv.addObject("pds", pds);
		mnv.setViewName(url);
		return mnv;
	}
	
	
	@RequestMapping("/detail.do")
	public ModelAndView detail(ModelAndView mnv, int pno) throws Exception{
		String url = "pds/detail.open";
		
		PdsVO pds = pdsService.getPds(pno);
		
		mnv.addObject("pds", pds);
		mnv.setViewName(url);
		return mnv;
	}
	

	@Resource(name="fileUploadPath")
	private String fileuploadPath;
	
	
	private List<AttachVO> saveFile(RegistPdsRequest registReq, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		
		if(registReq.getUploadFile() != null) {
			
			for(MultipartFile multi : registReq.getUploadFile()) {
				String fileName = UUID.randomUUID().toString().replace("-", "")
						+"$$"+multi.getOriginalFilename();
				
				File target = new File(fileuploadPath, fileName);
				
				if(!target.exists()) {
					target.mkdirs();
				}
				
				multi.transferTo(target);
				
				AttachVO attach = new AttachVO();
				attach.setUploadPath(fileuploadPath);
				attach.setFileName(fileName);
				attach.setFiletype(fileName.substring(fileName.lastIndexOf('.')+1).toUpperCase());
				
				MemberVO loginUser = (MemberVO)request.getSession().getAttribute("loginUser");
				attach.setAttacher(loginUser.getId());
				
				attachList.add(attach);
			}
		}
		return attachList;
	}
}
