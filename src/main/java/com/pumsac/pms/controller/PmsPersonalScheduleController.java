package com.pumsac.pms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pumsac.command.CalendarListToJsonDTO;
import com.pumsac.command.PersonalScheduleDTO;
import com.pumsac.command.SearchCriteria;
import com.pumsac.pms.service.PmsPersonalScheduleService;
import com.pumsac.pms.service.ProjectCommonService;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.PersonalScheduleVO;
import com.pumsac.vo.ProTeamVO;

@Controller
@RequestMapping("/pms/team/personalSchedule/*")
public class PmsPersonalScheduleController {
	
	private final String path = "pms/team/personalSchedule/";
	
	@Autowired
	private PmsPersonalScheduleService pmsPersonalScheduleService;
	
	@Autowired
	private ProjectCommonService projectCommonService;
	
	@RequestMapping(value="getPersonalData", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<CalendarListToJsonDTO>> getOfficailData(HttpSession session, PersonalScheduleVO personalScheduleVO, SearchCriteria cri) throws Exception{
		ResponseEntity<List<CalendarListToJsonDTO>> result = null;

		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		personalScheduleVO.setMemberId(loginUser.getEmail());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("personalScheduleVO", personalScheduleVO);
		map.put("cri", cri);
		
		try {
			Map<String, Object> dataMap = pmsPersonalScheduleService.getPersonalScheduleList(map);
			List<PersonalScheduleVO> personalScheduleList = (List<PersonalScheduleVO>) dataMap.get("personalScheduleList");
			List<CalendarListToJsonDTO> jsonList = new ArrayList<CalendarListToJsonDTO>();
	
			if (personalScheduleList != null) {
				for (PersonalScheduleVO osv : personalScheduleList) {
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
	public String getPersonalCalendarView(Model model, PersonalScheduleVO personalScheduleVO, SearchCriteria cri) throws Exception{
		String url = path + "calendarPersonal";
		model.addAttribute("projectId", personalScheduleVO.getProjectId());
		model.addAttribute("cri", cri);
		return url;
	}
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String getPersonalListView(Model model, HttpSession session, PersonalScheduleVO personalScheduleVO, SearchCriteria cri) throws Exception{
		String url = path + "listPersonal";
		
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		personalScheduleVO.setMemberId(loginUser.getEmail());

		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("personalScheduleVO", personalScheduleVO);
		map.put("cri", cri);
		
		Map<String, Object> dataMap = null;
		
		if (loginUser.getRole().equals("ROLE_ADMIN")) {
			dataMap = pmsPersonalScheduleService.getPersonalScheduleAllListForAdmin(map);
		} else {
			dataMap = pmsPersonalScheduleService.getPersonalScheduleList(map);
		}
		
		
		model.addAllAttributes(dataMap);
		model.addAttribute("projectId", personalScheduleVO.getProjectId());
		
		return url;
	}
	
	@RequestMapping(value="regist", method=RequestMethod.GET)
	public String registOfficalView(Model model, int projectId) throws Exception{
		String url = path + "registPersonal";
		model.addAttribute("projectId", projectId);
		return url;
	}
	@RequestMapping(value="regist", method=RequestMethod.POST)
	public String registPersonalSchedule(HttpSession session, PersonalScheduleDTO personalScheduleDTO) throws Exception{
		
		List<PersonalScheduleVO> list = personalScheduleDTO.ToPersonalScheduleVO(personalScheduleDTO);
		for (PersonalScheduleVO personalScheduleVO : list) {
			pmsPersonalScheduleService.registPersonalSchedule(personalScheduleVO);
		}
		
		return path + "personalSuccess";
	}
	@RequestMapping(value="detail", method=RequestMethod.GET)
	public String detailPersonalView(Model model, int no) throws Exception{
		String url = path + "detailPersonal";
		
		PersonalScheduleVO detailPersonalSchedule = pmsPersonalScheduleService.getDetailPersonalSchedule(no);
		
		model.addAttribute("detailPersonal", detailPersonalSchedule);
		return url;
	}
	
	@RequestMapping(value="getProjectMemberList", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ProTeamVO>> getProjectMemberList(int projectId) throws Exception{
		ResponseEntity<List<ProTeamVO>> entity = null;
		try {
			List<ProTeamVO> memList = projectCommonService.getProjectMember(projectId);
			if (memList != null) {
				entity = new ResponseEntity<>(memList, HttpStatus.OK);
			} else {
				entity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	@RequestMapping(value="modify", method=RequestMethod.GET)
	public String modifyPersonalView(Model model, int no) throws Exception {
		
		PersonalScheduleVO detail = pmsPersonalScheduleService.getDetailPersonalSchedule(no);
		
		model.addAttribute("detailPersonal", detail);
		
		return path + "modifyPersonal";
	}
	
	@RequestMapping(value="modify", method=RequestMethod.POST)
	public String modifyPersonal(Model model, HttpSession session, PersonalScheduleVO personalScheduleVO) throws Exception {
		
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser.getRole().equals("ROLE_ADMIN")) {
			pmsPersonalScheduleService.modifyPersonalScheduleByAdmin(personalScheduleVO);
		} else {
			pmsPersonalScheduleService.modifyPersonalScheduleByUser(personalScheduleVO);
		}
		
		
		
		return path + "personalSuccess";
	}
	
}
