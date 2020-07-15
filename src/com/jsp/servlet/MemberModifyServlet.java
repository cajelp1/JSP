package com.jsp.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.MemberVO;
import com.jsp.request.MemberRegistRequest;
import com.jsp.service.MemberServiceImpl;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeLogForException;
import com.jsp.utils.ViewResolver;

@WebServlet("/member/modify")
public class MemberModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/modify";
		String id = request.getParameter("id");
		
		MemberVO mem = null;
		
		try {
			mem = MemberServiceImpl.getInstance().getMember(id);
			request.setAttribute("member", mem);
			
		} catch (SQLException e) {
			e.printStackTrace();
			MakeLogForException.makeLog(request, e);
			url = "error/500_error";
		}
		
		ViewResolver.view(request, response, url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			MemberServiceImpl.getInstance().modify(member);
			
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
		
		ViewResolver.view(request, response, url);
		
	}

}
