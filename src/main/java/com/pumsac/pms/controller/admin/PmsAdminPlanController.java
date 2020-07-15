package com.pumsac.pms.controller.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pumsac.command.CalendarListToJsonDTO;
import com.pumsac.command.SearchCriteria;
import com.pumsac.pms.service.PlanService;
import com.pumsac.vo.PlanVO;

@Controller
@RequestMapping("/pms")
public class PmsAdminPlanController {

	@Autowired
	private PlanService planService;
	
	@GetMapping(value="/admin/team/calendar")
	public ModelAndView getTeamPlanCalendar(ModelAndView mnv,String projectId, SearchCriteria cri) {
		String url = "pms/dashboard/calendarOfficial";
		
		mnv.addObject("cri",cri);
		mnv.addObject("projectId",projectId);
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping(value="/admin/team/regist")
	public ModelAndView getRegistTeamPlan(ModelAndView mnv, String projectId) {
		String url = "pms/dashboard/admin/teamPlanRegist";
		
		mnv.addObject("projectId", projectId);
		mnv.setViewName(url);
		return mnv;
	}
	
	@PostMapping(value="/admin/team/regist")
	public ModelAndView postRegistTeamPlan(ModelAndView mnv, String projectId, PlanVO plan, HttpServletResponse response){
		
		try {
			planService.registTeamPlan(plan);
			mnv.setViewName("pms/dashboard/admin/calendarSuccess");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mnv;
	}
	
	@GetMapping(value="/admin/team/detail")
	public ModelAndView getTeamPlanDetail(String projectId, int no, ModelAndView mnv, HttpServletResponse response) {
		String url = "pms/dashboard/admin/teamPlanDetail";
		PlanVO plan = new PlanVO();
		
		try {
			plan = planService.getTeamPlanDetail(no);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		mnv.addObject("plan",plan);
		mnv.setViewName(url);
		return mnv;
	}
	
	@PostMapping(value="/admin/team/modify")
	public ModelAndView postTeamPlanDetail(ModelAndView mnv, String projectId, int no, PlanVO plan, HttpServletResponse response){
		
		try {
			planService.modifyPlan(plan);
			mnv.setViewName("pms/dashboard/admin/calendarSuccess");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return mnv;
	}
	
	@GetMapping(value="admin/team/modify")
	public ModelAndView getModifyTeamPlan(int no, ModelAndView mnv) {
		String url = "pms/dashboard/admin/teamPlanModify";
		PlanVO plan = new PlanVO();
		try {
			plan = planService.getTeamPlanDetail(no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		mnv.addObject("plan",plan);
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping(value="admin/team/remove")
	public ModelAndView removeTeamPlan(int no, ModelAndView mnv, HttpServletResponse response) {
		
		try {
			planService.removePlan(no);
			mnv.setViewName("pms/dashboard/admin/calendarSuccess");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mnv;
	}
	
	@GetMapping(value="/admin/team/calendar/getTeamData")
	@ResponseBody
	public ResponseEntity<List<CalendarListToJsonDTO>> getOfficialData(int projectId, String code/*, SearchCriteria cri */) {
		ResponseEntity<List<CalendarListToJsonDTO>> result = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectId", projectId);
		map.put("code", code);
				
		try {

			List<PlanVO> planList = planService.getTeamPlan(projectId, code);

			List<CalendarListToJsonDTO> jsonList = new ArrayList<CalendarListToJsonDTO>();
			if (planList != null) {

				for (PlanVO planVO : planList) {
					CalendarListToJsonDTO cd = new CalendarListToJsonDTO(planVO.getNo() ,planVO.getTitle(), planVO.getStartDate() , planVO.getEndDate(), planVO.getColor());
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
	
	
	
}
