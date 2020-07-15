package com.jsp.action.member;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.request.MemberRegistRequest;
import com.jsp.service.MemberService;
import com.jsp.service.MemberServiceImpl;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeLogForException;

public class MemberModifyAction implements Action {
	
	private MemberService memberService;// = MemberServiceImpl.getInstance();
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "member/modify_success";
		
		String id        = request.getParameter("id");
		String pwd       = request.getParameter("pwd");
		String name      = request.getParameter("name");
		String email     = request.getParameter("email");
		String picture   = request.getParameter("picture");
		String[] phone   = request.getParameterValues("phone");
		String authority = request.getParameter("authority");
		
		System.out.println(name);
		
		MemberRegistRequest memReq = new MemberRegistRequest(id, pwd, authority, email, phone, picture, name);
		
		MemberVO member = memReq.toMemberVO();
		
		try {
			memberService.modify(member);
			
			HttpSession session = request.getSession();
			MemberVO loginUser=(MemberVO)session.getAttribute("loginUser");
			//만약 로그인한 사람의 정보가 수정되면 세션에 들어있는 정보도 수정한다.
			if(member.getId().equals(loginUser.getId())) {
				session.setAttribute("loginUser", member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			MakeLogForException.makeLog(request, e);
			url="member/modify_fail";
			
			//업로드 되었던 파일도 지운다.. 왜 지우지? db에 업데이트가 안됐으니 지우는거구만.. 근데 이미 예전 파일은 지워버렸을텐디?;
			String oldFileName = member.getPicture();
			String uploadPath=GetUploadPath.getUploadPath("member.picture.upload");
			File oldFile=new File(uploadPath+File.separator+oldFileName);
			if(oldFile.exists()) {
				oldFile.delete();
			}
			
		}
		
		return url;
	}

}
