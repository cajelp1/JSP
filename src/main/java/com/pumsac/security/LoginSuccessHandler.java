package com.pumsac.security;

import com.pumsac.service.MemberService;
import com.pumsac.util.PasswordEncryption;
import com.pumsac.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	MemberService meberservice;
	public void setMeberservice(MemberService meberservice) {
		this.meberservice = meberservice;
	}
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {


		String id = authentication.getName();
		String password= (String) authentication.getCredentials();

		try {
			MemberVO loginUser = (MemberVO) meberservice.getMember(id, null);
			System.out.println(loginUser.toString());
			if(loginUser.getPassword().equals(PasswordEncryption.passwordEncryption(password))) {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", loginUser);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		User user = (User) authentication.getDetails();

		MemberVO loginUser = user.getMemberVO();

		HttpSession session = request.getSession();

		session.setAttribute("loginUser", loginUser);
		session.setMaxInactiveInterval(20 * 60); // second

		// 로그인 로그 작성
		

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		if (!user.isAccountNonLocked()) {
			out.println("<script>");
			out.println("alert('휴직상태로 권한이 일반사용자로 제한됩니다.');");
			out.println("location.href='/" + request.getContextPath() + "';");
			out.println("</script>");
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}

	}

}
