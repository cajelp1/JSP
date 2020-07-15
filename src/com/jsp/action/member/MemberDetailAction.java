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

public class MemberDetailAction implements Action {
	
	private MemberService memberService;// = MemberServiceImpl.getInstance();
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//화면결정
		String url = "member/detail";
		
		//파라미터 처리
		String id = request.getParameter("id");
		
		//서비스 요청 겸 에러페이지 분할
		MemberVO mem = null;
		
		try {
			mem = memberService.getMember(id);
			request.setAttribute("member", mem);
			
		} catch (SQLException e) {
			e.printStackTrace();
			MakeLogForException.makeLog(request, e);
			//500 으로 보내야하나? 404로 보내야하나?
			url = "error/500_error";
		}
		
		//결과에 따른 화면 분할... 근데 여기서는 mem이 null이 될 수 없는데. 고로 그냥 보내면 될 거라구 생각함....
		//그래도 혹시모르니 일단 보낼까?
		if(mem == null) {
			System.out.println("mem이 null입니다");
			url = "error/500_error";
		}
		
		return url;
	}

}
