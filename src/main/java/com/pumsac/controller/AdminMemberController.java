package com.pumsac.controller;

import com.pumsac.command.SearchCriteria;
import com.pumsac.pms.service.ProjectCommonService;
import com.pumsac.service.AdminService;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.ProTeamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("dsp/admin/member")
public class AdminMemberController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private ProjectCommonService projectCommonService;
	
	@RequestMapping("/list")
	public ModelAndView getMemberList(ModelAndView mnv, SearchCriteria cri) {
		String url = "site/admin/member/list";
		Map<String, Object> map = null;
		//SearchCriteria cri = new SearchCriteria();
		try {
			map = adminService.getMemberList(cri);
		} catch (Exception e) {
			e.printStackTrace();
			mnv.setViewName("error/500_error");
			return mnv;
		}
		mnv.addAllObjects(map);
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public ModelAndView getMemberDetail(ModelAndView mnv, String email) {
		String url = "site/admin/member/detail";
		
		List<ProTeamVO> teamList = null;
		MemberVO member = null;
		try {
			member = adminService.getMemberDetail(email);
			teamList = projectCommonService.getProjectList(email);
		} catch (Exception e) {
			e.printStackTrace();
			url = "error/500_error";
		}
		mnv.addObject("member", member);
		mnv.addObject("projectList", teamList);
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public ModelAndView modifyMemberDetail(ModelAndView mnv, HttpSession session, String email, String role) {
		String url = "site/admin/member/modify_success";
		
		if(session != null && session.getAttribute("loginUser")!=null) {
			MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
			if(loginUser.getEmail().equals(email)) {
				mnv.setViewName("site/admin/member/modify_fail_self");
				return mnv;
			}
		}
		
		MemberVO member = new MemberVO();
		member.setEmail(email);
		member.setRole(role);
		
		try {
			adminService.modifyMember(member);
		} catch (Exception e) {
			url="site/admin/member/modify_fail";
			e.printStackTrace();
		}
		mnv.setViewName(url);
		return mnv;
	}
	
	
	
}
