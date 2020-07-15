package com.jsp.action.pds;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.service.PdsService;

public class RemovePdsAction2 implements Action {
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "pds/remove_success";
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		String[] list = request.getParameterValues("uploadPath");
		
		if(list!=null && list.length>0) {
			for(String path : list) {
				File target = new File(path);
				if(!target.exists()) {
					url = "pds/remove_fail";
					request.setAttribute("msg", "첨부파일 위치를 찾을 수 없습니다");
					return url;
				}else {
					target.delete();
					try {
						pdsService.removeAttach(pno);
					} catch (SQLException e) {
						url = "pds/remove_fail";
						request.setAttribute("msg", "게시글의 첨부파일을 찾을 수 없습니다");
						return url;
					}
				}
			}
		}
		
		try {
			pdsService.remove(pno);
		} catch (SQLException e) {
			url = "pds/remove_fail";
			request.setAttribute("msg", "게시글을 지울 수 없습니다");
		}
		
		return url;
	}

}
