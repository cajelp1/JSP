package com.jsp.action.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;
import com.jsp.service.MemberServiceImpl;
import com.jsp.utils.MakeLogForException;

public class MemberModifyFormAction implements Action {
	
	private MemberService memberService;// = MemberServiceImpl.getInstance();
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "member/modify";
		String id = request.getParameter("id");
		
		MemberVO mem = null;
		
		try {
			mem = memberService.getMember(id);
			request.setAttribute("member", mem);
			
		} catch (SQLException e) {
			e.printStackTrace();
			MakeLogForException.makeLog(request, e);
			url = "error/500_error";
		}
		
		return url;
	}

}
