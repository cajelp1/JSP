package com.pumsac.pms.controller.admin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pumsac.pms.service.PmsDashBoardService;
import com.pumsac.pms.service.PmsPersonalScheduleService;
import com.pumsac.vo.PersonalScheduleVO;

@Controller
@RequestMapping("/pms/admin/task")
public class PmsAdminTaskController {

	@Autowired
	private PmsPersonalScheduleService taskService;
	
	@Autowired
	private PmsDashBoardService pmsDashBoardService;
	
	/**
	 * @내용 : 관리자가 개인업무에 대한 완료요청을 승인할때.  
	 */
	@RequestMapping(value="/approve")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> approveCompletedSchedule(int no, PersonalScheduleVO schedule) {
    	ResponseEntity<Map<String, Boolean>> entity = null;
    	Map<String, Boolean> result = new HashMap<String, Boolean>();
    	schedule.setNo(no);
    	System.out.println(schedule.toString());
    	System.out.println(schedule.getReason());
    	try {
    		pmsDashBoardService.approveCompletedSchedule(schedule);
    		result.put("result", true);
    	} catch (SQLException e) {
    		result.put("result", false);
		}
    	entity = new ResponseEntity<Map<String,Boolean>>(result,HttpStatus.OK);
    	
    	return entity;
    }
	

    /**
     * @내용 : 상세내역에서 재요청버튼을 클릭했을때 실행되는 메소드
     */
    @RequestMapping(value="/request")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> requestCompletedSchedule(int no, PersonalScheduleVO schedule) {
    	ResponseEntity<Map<String, Boolean>> entity = null;
    	Map<String, Boolean> result = new HashMap<String, Boolean>();
    	schedule.setNo(no);
    	System.out.println(schedule);
    	try {
    		pmsDashBoardService.requestCompletedSchedule(schedule);
    		result.put("result", true);
    	} catch (SQLException e) {
    		result.put("result", false);
		}
    	entity = new ResponseEntity<Map<String,Boolean>>(result,HttpStatus.OK);
    	return entity;
    }
    
    /**
	 * @내용 : 관리자가 개인업무에 대한 내용을 수정하는 버튼을 클릭했을때.  
	 */
	@GetMapping(value="/modify")
	public ModelAndView getModifyTask(int no, ModelAndView mnv) {
		String url = "pms/dashboard/admin/task_modify";
		
		PersonalScheduleVO task = null;
		try {
			task = taskService.getDetailPersonalSchedule(no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		mnv.addObject("detailPersonal",task);
		mnv.setViewName(url);
		return mnv;
	}
	
	/**
	 * @내용 : 관리자가 개인업무에 대한 내용을 수정했을 때  
	 */
	@PostMapping(value="/modify")
	public ModelAndView postModifyTask(int no, ModelAndView mnv, PersonalScheduleVO task) {
		
		try {
			taskService.modifyPersonalScheduleByAdmin(task);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		mnv.setViewName("pms/team/personalSchedule/personalSuccess");
		
		return mnv;
	}
	
	/**
	 * @내용 : 관리자가 개인업무를 삭제할때  
	 */
	@PostMapping(value="/remove")
	@ResponseBody
	public ResponseEntity<String> removeTask(int no) {
		ResponseEntity<String> entity = null;
		try {
			taskService.removeTask(no);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
}
