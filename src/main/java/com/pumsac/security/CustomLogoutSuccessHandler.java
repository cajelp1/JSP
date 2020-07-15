package com.pumsac.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pumsac.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


import com.pumsac.service.MemberService;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
@Autowired
	private MemberService employeeService;
	public void setEmployeeService(MemberService employeeService) {
		this.employeeService = employeeService;
	}
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
								HttpServletResponse response,
								Authentication auth)
			throws IOException, ServletException {
		if (auth != null && auth.getDetails() != null) {
			try {
				User user = (User)auth.getDetails();
				MemberVO loginUser = user.getMemberVO();
				HttpSession session=request.getSession(true);
				session.invalidate();
				//	employeeService.recentLoginTime(loginUser.getEmail());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		response.sendRedirect(request.getContextPath()+"/");
	}

}








