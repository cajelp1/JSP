package com.spring.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
	
	@RequestMapping("/admin/main")
	public void admins() {}
	
	@RequestMapping("/home/main")
	public void homes() {}
	
	@RequestMapping("/manager/main")
	public void managers() {}
	
	@RequestMapping("/member/main")
	public void members() {}
	
}
