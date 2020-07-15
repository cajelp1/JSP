package com.pumsac.security;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;

import com.pumsac.service.MemberService;
import com.pumsac.vo.MemberVO;

public class SessionDestoryListener implements ApplicationListener<SessionDestroyedEvent> {

	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}


	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {
		List<SecurityContext> contexts = event.getSecurityContexts();
		if (!contexts.isEmpty()) {
			for (SecurityContext ctx : contexts) {
				Authentication auth = ctx.getAuthentication();

				if (auth != null && auth.getDetails() != null) {
					try {
						User user = (User) auth.getDetails();
						MemberVO loginUser = user.getMemberVO();

//./						employeeService.recentLoginTime(loginUser.getId());

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

}
