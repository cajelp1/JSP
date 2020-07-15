package com.pumsac.pms.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pumsac.command.SearchCriteria;
import com.pumsac.pms.service.PmsDashBoardService;
import com.pumsac.vo.MemberVO;

@Controller
@RequestMapping("/pms")
public class PmsCommonContoller {
    
    @Autowired
    PmsDashBoardService pmsDashBoardService;
    
	 @GetMapping("/")
	    public ModelAndView pmsDashboardProjectList(SearchCriteria cri, ModelAndView modelAndView, HttpSession session) throws SQLException {
	        MemberVO loginSessionInfo = (MemberVO) session.getAttribute("loginUser");
	        cri.setMemberId(loginSessionInfo.getEmail());

	        List<Map<String, String>> project = pmsDashBoardService.getProjectListOrderByCondition(cri);

	        modelAndView.addObject("loginUser", loginSessionInfo);
	        modelAndView.addObject("projectList", project);
	        modelAndView.setViewName("pms/dashboard/project_list");
	        return modelAndView;
	    }
	@PostMapping("/")
	public ModelAndView ProjectSearchByKeywordAndOrderConditionList(SearchCriteria cri, ModelAndView modelAndView, HttpSession session) throws SQLException {
		MemberVO loginSessionInfo = (MemberVO) session.getAttribute("loginUser");
		cri.setMemberId(loginSessionInfo.getEmail());

		List<Map<String, String>> project = pmsDashBoardService.getProjectListOrderByCondition(cri);

		modelAndView.addObject("loginUser", loginSessionInfo);
		modelAndView.addObject("projectList", project);
		modelAndView.setViewName("pms/dashboard/project_list");
		return modelAndView;
	}

	 
	 
}
