package com.pumsac.security;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pumsac.util.PasswordEncryption;
import com.pumsac.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.pumsac.dao.MemberDao;


public class CustomAuthentication implements AuthenticationProvider{
	@Autowired
	private MemberDao memberDao;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		String login_id = (String) auth.getPrincipal(); // 로그인 시도한 ID를 가져온다
		String login_pwd = (String) auth.getCredentials(); //로그인 시도한 Password 를 가져온다.
		System.out.println("=======>"+login_pwd);
		String temp="";
		try {
			temp= PasswordEncryption.passwordEncryption(login_pwd);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println("=========>"+temp);
		MemberVO employee=null;
		try {
			employee=memberDao.selectMemberByEmail(login_id);
		}catch(SQLException e) {
			throw new BadCredentialsException("Internal server error!");
		}

		if(employee != null && temp.equals(employee.getPassword())) {
			User loginUser = new User(employee);

			if(!loginUser.isEnabled()) { // 사용 제제 (퇴사자)
				throw new DisabledException("퇴사한 상태입니다. 관리자에게 문의바랍니다.");
			}else {
				List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
				// 사용자에게 권한 부여
				if(loginUser.isAccountNonLocked()) {
					roles.add(new SimpleGrantedAuthority(employee.getRole()));
				}else {
					roles.add(new SimpleGrantedAuthority("ROLE_USER"));
				}

				// 스프링 시큐리티 내부 클래스로 인증 토큰 생성한다.
				UsernamePasswordAuthenticationToken result =
						new UsernamePasswordAuthenticationToken(employee.getEmail(),
								employee.getPassword(), roles);
				// 전달할 내용을 설정한 후
				result.setDetails(loginUser);

				return result;
			}
		}else {
			// 실패시 예외처리
			throw new BadCredentialsException("아이디 혹은 패스워드가 일치하지 않습니다.");
		}

	}



	@Override
	public boolean supports(Class<?> arg) {
		 /* UsernamePasswordAuthenticationToken 리턴할 때
		 파라미터와 같은 레퍼런스인지 비교를하고 리턴을 한다.
		 default-target-url 로 전송된다. */
		return arg.equals(UsernamePasswordAuthenticationToken.class);
	}

}







