package com.spring.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.dto.MemberVO;
import com.spring.request.SearchCriteria;
import com.spring.service.MemberService;


@Controller
@RequestMapping("/member/*")
public class MemberActionController {
	
	@Autowired
	private MemberService memberService;// = MemberServiceImpl.getInstance();
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	
//	@RequestMapping(value="list.do",method=RequestMethod.GET)
	@RequestMapping("list.do")
	public String list_GET(SearchCriteria cri, Model model) throws Exception {
		String url="member/list.page";
		
		Map<String, Object> dataMap = memberService.getMemberList(cri);
		dataMap.put("title","회원리스트"); //tiles 사용하며 제목을 달기 위한 것
		
//		model.addAttribute("pageMaker", (PageMaker)dataMap.get("pageMaker"));
//		model.addAttribute("memberList", (List<MemberVO>)dataMap.get("memberList"));
		
		model.addAllAttributes(dataMap);
		
		return url;
	}
	
	
	@RequestMapping("detail.do")
	public String detail(String id, Model model) throws Exception {
		String url = "member/detail.open";
		
		MemberVO member = memberService.getMember(id);
		
		model.addAttribute("member",member);
		
		return url;
	}
	
	
	
	
	
}
