package com.pumsac.pms.controller.admin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pumsac.command.SearchCriteria;
import com.pumsac.pms.service.PmsDashBoardService;
import com.pumsac.pms.service.PmsPersonalScheduleService;
import com.pumsac.vo.PersonalScheduleVO;

@Controller
@RequestMapping("/pms/admin")
public class pmsAdminPersonalController {
	
    @Autowired
    private PmsDashBoardService pmsDashBoardService;
    
    @Autowired
    private PmsPersonalScheduleService pmsPersonalScheduleService;
    
    /**
     * @내용 : 프로젝트 매니저 페이지에서 등록된 개인업무 리스트 보여주기
     */
    @RequestMapping(value="/personalSchedule/list", method=RequestMethod.GET)
    public ModelAndView getPersonalScheduleList(ModelAndView mnv, int projectId, PersonalScheduleVO personalScheduleVO, SearchCriteria cri) {
    	String url = "pms/dashboard/personalSchedule_recentList";
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("personalScheduleVO", personalScheduleVO);
    	map.put("cri", cri);
    	
//    	List<PersonalScheduleVO> schedule = null;
    	try {
    		map = pmsPersonalScheduleService.getPersonalScheduleAllListForAdmin(map);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	mnv.addObject("projectId", projectId);
    	mnv.addObject("dataMap", map);
    	mnv.setViewName(url);
    	return mnv;
    }
    
    /**
     * @내용 : 진척률이 100%이고, 완료승인이 n상태인 업무목록 리스트 보여주기
     */
    @RequestMapping("/personalSchedule/completed")
    public ModelAndView getCompletedPersonalSchedule(int projectId, ModelAndView mnv) {
    	String url = "pms/dashboard/personalSchedule_completed";
    	
    	List<PersonalScheduleVO> list = null; 
    	try {
			list = pmsDashBoardService.getCompletedPersonalSchedule(projectId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	mnv.addObject("projectId", projectId);
    	mnv.addObject("list", list);
    	mnv.setViewName(url);
    	return mnv;
    }
    
    /**
     * @내용 : 개인업무등록창을 보여주는 메소드
     */
    @RequestMapping(value="/personalSchedule/regist",method=RequestMethod.GET)
    public ModelAndView getPersonalScheduleRegist(int projectId, ModelAndView mnv) {
    	String url = "pms/dashboard/admin/personalSchedueRegist";
    	
    	mnv.addObject("projectId", projectId);
    	mnv.setViewName(url);
    	return mnv;
    }
    
    /**
     * @내용 : 개인업무를 등록하는 메소드
     * @문제점 : 현재 여러명에게 업무를 동시에 등록하는 기능이 있으나 그것을 적용하지 않음.
     */
    @RequestMapping(value="/personalSchedule/regist",method=RequestMethod.POST)
    public String postPersonalScheduleRegist(int projectId, PersonalScheduleVO schedule) throws Exception{
    	String url = "pms/team/personalSchedule/personalSuccess";
		
    	pmsPersonalScheduleService.registPersonalSchedule(schedule);
    		
    	return url;
    }
    
    /**
     * @내용 : 개인업무에 대한 상세내역을 보여주는 메소드
     */
    @RequestMapping("/personalSchedule/detail")
    public ModelAndView getCompletedPersonalScheduleDetail(int no, ModelAndView mnv) {
    	String url = "pms/dashboard/personalSchedule_detail";
    	PersonalScheduleVO schedule = null;
    	try {
    		schedule = pmsPersonalScheduleService.getDetailPersonalSchedule(no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	mnv.addObject("schedule",schedule);
    	mnv.setViewName(url);
    	return mnv;
    }
    
}
