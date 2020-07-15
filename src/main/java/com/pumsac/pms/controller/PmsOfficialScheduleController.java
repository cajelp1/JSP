package com.pumsac.pms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pumsac.command.CalendarListToJsonDTO;
import com.pumsac.command.SearchCriteria;
import com.pumsac.pms.service.PmsOfficialScheduleService;
import com.pumsac.vo.PlanVO;

@Controller
@RequestMapping("/pms/team/officialSchedule/")
public class PmsOfficialScheduleController {
	
	private final String path = "pms/team/officialSchedule/";
	
	@Autowired
	private PmsOfficialScheduleService pmsOfficialScheduleService;
	
	@RequestMapping(value="getOfficailData", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<CalendarListToJsonDTO>> getOfficailData(int projectId, SearchCriteria cri) throws Exception{
		ResponseEntity<List<CalendarListToJsonDTO>> result = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectId", projectId);
		map.put("cri", cri);
		try {
			Map<String, Object> dataMap = pmsOfficialScheduleService.getOfficalScheduleList(map);
			List<PlanVO> officalScheduleList = (List<PlanVO>) dataMap.get("officalScheduleList");
			List<CalendarListToJsonDTO> jsonList = new ArrayList<CalendarListToJsonDTO>();
			
			if (officalScheduleList != null) {
				for (PlanVO osv : officalScheduleList) {
					CalendarListToJsonDTO cd = new CalendarListToJsonDTO(osv.getNo() ,osv.getTitle(), osv.getStartDate(), osv.getEndDate(), osv.getColor());
					jsonList.add(cd);
				}
			}
			result = new ResponseEntity<List<CalendarListToJsonDTO>>(jsonList, HttpStatus.OK);
		} catch (Exception e) {
			result = new ResponseEntity<List<CalendarListToJsonDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@RequestMapping(value="calendar", method=RequestMethod.GET)
	public String getOfficialCalendarView(Model model, int projectId, SearchCriteria cri) throws Exception{
		String url = path + "calendarOfficial";

		model.addAttribute("projectId", projectId);
		model.addAttribute("cri", cri);

		return url;
	}
	
	@RequestMapping(value="list")
	public String getOfficialListView(Model model, int projectId, SearchCriteria cri) throws Exception{
		String url = path + "listOfficial";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectId", projectId);
		map.put("cri", cri);
		
		Map<String, Object> dataMap = pmsOfficialScheduleService.getOfficalScheduleList(map);
		
		model.addAllAttributes(dataMap);
		model.addAttribute("projectId", projectId);
		
		return url;
	}
	
	@RequestMapping(value="regist", method=RequestMethod.GET)
	public String registOfficalView(Model model, String projectId) throws Exception{
		String url = path + "registOfficial";
		model.addAttribute("projectId", projectId);
		return url;
	}
	@RequestMapping(value="regist", method=RequestMethod.POST)
	public String registOfficialSchedule(Model model, PlanVO plan) throws Exception{
		
		pmsOfficialScheduleService.registOffcialSchedule(plan);
		
		return path + "officialSuccess";
	}
	
	@RequestMapping(value="detail", method=RequestMethod.GET)
	public String detailOfficalView(Model model, int no, int projectId) throws Exception{
		String url = path + "detailOfficial";
		
		PlanVO detailOfficialSchedule = pmsOfficialScheduleService.getDetailOfficialSchedule(no);
		
		model.addAttribute("plan", detailOfficialSchedule);
		return url;
	}
	
	@RequestMapping(value="modify", method=RequestMethod.GET)
	public String modifyOfficalView(Model model, int no) throws Exception{
		String url = path + "modifyOfficial";
		
		PlanVO detailOfficialSchedule = pmsOfficialScheduleService.getDetailOfficialSchedule(no);
		model.addAttribute("detailOfficial", detailOfficialSchedule);
		
		return url;
	}
	@RequestMapping(value="modify", method=RequestMethod.POST)
	public String modifyOfficalSchedule(PlanVO plan) throws Exception{
		
		pmsOfficialScheduleService.modifyOffcialSchedule(plan);
		
		return path + "officialSuccess";
	}
	
	@RequestMapping(value="remove")
	public String removeOfficalSchedule(int no) throws Exception{
		pmsOfficialScheduleService.removeOfficalSchedule(no);
		return path + "officialSuccess";
	}
	
	
}
