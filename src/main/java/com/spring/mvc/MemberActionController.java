package com.spring.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.request.member.RegistMemberRequest;


@Controller //이 어노테이셔이 반드시 필요함!!!
@RequestMapping("/member/*")
public class MemberActionController {
	
	@RequestMapping(value="regist",method=RequestMethod.GET)
	public String regist_GET() throws Exception{
		String url = "member/regist";
		return url;
	}
	
	@RequestMapping(value="regist",method=RequestMethod.POST)
	public String regist_POST (RegistMemberRequest registReq,
			@RequestParam(value="password")String pwd,		//password값을 찾아서 pwd에 옮겨줌
								String id, String password,
			@RequestParam(defaultValue="대전광역시")String address,
			 					HttpServletRequest request,
			 					HttpServletResponse response,
			 					HttpSession session
							 ) throws Exception{
		String url = "redirect:http://www.ddit.or.kr";
		
		System.out.println(registReq);
		System.out.println(pwd);
		System.out.println(id+"+"+password);
		System.out.println(request);
		System.out.println(response);
		System.out.println(session);
		System.out.println();
		System.out.println(address);
		
		return url;
	}
	
	
	
}
