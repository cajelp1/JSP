package com.pumsac.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pumsac.vo.MemberVO;

public class User implements UserDetails {

	private MemberVO member;

	public User() {
	};


	public User(MemberVO member) {
		this.member = member;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {// 기간제 계정의 경우 기간만료 여부
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // 사용 제제 여부 : 휴직
		boolean result = true;
		if (member.getRole().equals("ROLE_SLEEP"))
			result = false;
		return result;
	}

	@Override
	public boolean isCredentialsNonExpired() { // 인증정보 만료 여부 (패스워드 만료)
		return true;
	}

	@Override
	public boolean isEnabled() {// 휴면계정 여부 : 퇴사
		boolean result = true;
		if (member.getRole().equals("ROLE_DISABLED"))
			result = false;
		return result;
	}

	public MemberVO getMemberVO() {
		return this.member;
	}
}
