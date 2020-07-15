package com.pumsac.pms.controller;

import com.pumsac.command.SearchCriteria;
import com.pumsac.pms.service.PlanService;
import com.pumsac.pms.service.PmsDashBoardService;
import com.pumsac.pms.service.PmsPdsService;
import com.pumsac.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pms/user")
public class PmsUserDashboardController {
	/*
	@Autowired
	private PmsUserDashboardService pmsUserDashboardService;
	*/
	@Autowired
	private PlanService planService;
	
	@Autowired
	private PmsPdsService pmsPdsService;
	
	@Autowired
	private PmsDashBoardService pmsDashBoardService;
	
	@GetMapping("/main")
	public ModelAndView doSearchPmsDashboardProjectList(SearchCriteria cri,int projectId, HttpSession session, ModelAndView mnv) throws SQLException {
		String url = "pms/dashboard/member_dashboard";
		
		Map<String, Object> dataMap = pmsDashBoardService.getProjectInfoByProjectId(projectId);
		ProjectVO project = (ProjectVO) dataMap.get("project");
		
		Map<String, Object> dataMap2 = pmsDashBoardService.getProjectMemberByProjectId(cri);
		List<MemberVO> member = (List<MemberVO>)dataMap2.get("projectMember");
		
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		List<PersonalScheduleVO> task = pmsDashBoardService.getPersonalTasks(projectId, loginUser.getEmail());
		
		List<ProBoardMasterVO> boardMaster = pmsPdsService.getBoadMasterList(projectId);
		
		mnv.addObject("dataMap", dataMap);
		mnv.addObject("project",project);
		mnv.addObject("boardMaster",boardMaster);
		mnv.addObject("member",member);
		mnv.addObject("task",task);
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping("/list")
	public ModelAndView getPmsDashboardProjectList(SearchCriteria cri, int projectId, ModelAndView mnv) throws SQLException {
		String url = "pms/dashboard/member_user_list";
		
		Map<String, Object> dataMap = pmsDashBoardService.getProjectMemberByProjectId(cri);
    	
    	mnv.addObject("dataMap",dataMap);
		mnv.setViewName(url);
		return mnv;
	}
	
	/*
	@RequestMapping(value="/calendar/getTeamData", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<CalendarListToJsonDTO>> getOfficialData(String projectId, String code, SearchCriteria cri ) {
		ResponseEntity<List<CalendarListToJsonDTO>> result = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectId", projectId);
		map.put("code", code);
				
		try {

			List<PlanVO> planList = planService.getTeamPlan(projectId, code);

			List<CalendarListToJsonDTO> jsonList = new ArrayList<CalendarListToJsonDTO>();
			if (planList != null) {

				for (PlanVO planVO : planList) {
					CalendarListToJsonDTO cd = new CalendarListToJsonDTO(planVO.getNo() ,planVO.getTitle(), new SimpleDateFormat("yyyy-MM-dd").parse(planVO.getStartDate()) , new SimpleDateFormat("yyyy-MM-dd").parse(planVO.getEndDate()), planVO.getColor());
					jsonList.add(cd);
				}
			}	

			result = new ResponseEntity<List<CalendarListToJsonDTO>>(jsonList, HttpStatus.OK);
		} catch (Exception e) {
			result = new ResponseEntity<List<CalendarListToJsonDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return result;
	}
	*/

	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public ModelAndView getTeamPlanDetail(String projectId, int no, ModelAndView mnv, HttpServletResponse response) {
		PlanVO plan = new PlanVO();
		
		try {
			plan = planService.getTeamPlanDetail(no);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		mnv.addObject("plan",plan);

		return mnv;
	}
	
}
