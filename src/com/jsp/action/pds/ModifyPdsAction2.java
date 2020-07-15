package com.jsp.action.pds;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.action.Action;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;

public class ModifyPdsAction2 implements Action {
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "pds/modify_success";
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			url="error/500_error";
			return url;
		}
		
		
		//formItems 리스트로 꺼내기
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> formItems = null;
		
		try {
			formItems = upload.parseRequest(request);
		} catch (FileUploadException e) {
			url="error/500_error";
			return url;
		}
		
		
		//pds만 저장하기
		PdsVO pdsVO = new PdsVO(); 
		if(formItems != null && formItems.size() > 0) {
			for(FileItem item : formItems) {
				switch (item.getFieldName()) {
				case "pno" : 
					pdsVO.setPno(Integer.parseInt(item.getString("utf-8")));
					break;
				case "writer" :
					pdsVO.setWriter(item.getString("utf-8"));
					break;
				case "title" :
					pdsVO.setTitle(item.getString("utf-8"));
					break;
				case "content" : 
					pdsVO.setContent(item.getString("utf-8"));
					break;
				}
			}
			try {
				pdsService.modify(pdsVO);
				request.setAttribute("pno", pdsVO.getPno());
			} catch (SQLException e) {
				url="pds/modify_fail";
				return url;
			}
		}
		
		return url;
	}

}
