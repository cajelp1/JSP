package com.pumsac.pms.controller.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pumsac.command.SearchCriteria;
import com.pumsac.pms.service.PmsDashBoardService;
import com.pumsac.pms.service.PmsPersonalScheduleService;
import com.pumsac.pms.service.ProjectCommonService;
import com.pumsac.service.MemberService;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.PersonalScheduleVO;
import com.pumsac.vo.ProTeamVO;
import com.pumsac.vo.ProjectVO;

@Controller
@RequestMapping("/pms/admin")
public class PmsAdminDashboardController {
	
    @Autowired
    private PmsDashBoardService pmsDashBoardService;
    
    @Autowired
    private PmsPersonalScheduleService pmsPersonalScheduleService;
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private ProjectCommonService projectCommonService;
    
    

    @GetMapping("/main")
    public ModelAndView doSearchPmsDashboardProjectList(int projectId, SearchCriteria cri, ModelAndView modelAndView, HttpSession session) throws SQLException {
    	String url = "pms/dashboard/user_dashboard";
    	Map<String, Object> dataMap = pmsDashBoardService.getProjectInfoByProjectId(projectId);
    	ProjectVO project = (ProjectVO) dataMap.get("project");
    	modelAndView.addObject("projectList",pmsDashBoardService.getProjectListOrderByCondition(cri));
    	
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
    	
        /**
         * 프로젝트 등록자가 관리자라
         * 로그인한 유저의 Email과 등록자의 Email을 비교해서 관리자 Dashboard로 보낼지 사용자 DashBoard로 보낼지 결정
         */

        if(loginUser != null && loginUser.getEmail().equals(project.getRegister())) {
        	url = "pms/dashboard/admin/admin_dashboard";
        	session.setAttribute("projectManager", loginUser.getEmail());
        }
        
        modelAndView.addObject("dataMap",dataMap);
        modelAndView.setViewName(url);
        return modelAndView;
    }
    
    @GetMapping("/member/list")
    public ModelAndView getProjectMemberList (SearchCriteria cri, ModelAndView modelAndView, HttpSession session) throws SQLException {
    	String url = "pms/dashboard/member_list";
    	
    	Map<String, Object> dataMap = pmsDashBoardService.getProjectMemberByProjectId(cri);
    	
    	modelAndView.addObject("dataMap",dataMap);
    	modelAndView.setViewName(url);
    	return modelAndView;
    }
    
    
    
    
    @RequestMapping(value="/member/setManager")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> setProjectManager(String email, int projectId, HttpSession session) {
    	ResponseEntity<Map<String, Boolean>> entity = null;
    	Map<String, Boolean> result = new HashMap<String, Boolean>();
    	
    	try {
    		pmsDashBoardService.changeProjectManager(email, projectId, session);
    		result.put("result", true);
    	} catch (SQLException e) {
    		result.put("result", false);
		}
    	
    	entity = new ResponseEntity<Map<String,Boolean>>(result,HttpStatus.OK);
    	return entity;
    }
    
    @RequestMapping(value="/member/detail")
    public ModelAndView getProjectMemberDetail(String id, int projectId, ModelAndView mnv) {
    	String url = "pms/dashboard/member_detail";
    	MemberVO member = new MemberVO();
    	List<PersonalScheduleVO> task = new ArrayList<PersonalScheduleVO>();
    	try {
			member = memberService.checkEmail(id);
			PersonalScheduleVO paramTask = new PersonalScheduleVO();
			paramTask.setMemberId(id);
			paramTask.setProjectId(projectId);
			task = pmsPersonalScheduleService.getTaskForProjectManager(paramTask);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

    	mnv.addObject("task", task);
    	mnv.addObject("member", member);
    	mnv.setViewName(url);
    	return mnv;
    }
    
    @RequestMapping(value="/member/remove")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> removeProjectMember(String email, int projectId, HttpSession session) {
    	ResponseEntity<Map<String, Boolean>> entity = null;
    	Map<String, Boolean> result = new HashMap<String, Boolean>();

    	try {
    		pmsDashBoardService.removeProjectMember(email, projectId, session);
    		result.put("result", true);
    	} catch (SQLException e) {
    		result.put("result", false);
		}
    	entity = new ResponseEntity<Map<String,Boolean>>(result,HttpStatus.OK);
    	return entity;
    }
    
    @RequestMapping(value="/member/regist", method=RequestMethod.GET)
    public ModelAndView registProjectmember(SearchCriteria cri, ModelAndView mnv, int projectId) {
    	String url = "pms/dashboard/member_regist";
    	cri.setProjectId(projectId);
    	System.out.println(projectId);
    	try {
    		Map<String,Object> dataMap = pmsDashBoardService.getRegistrableProjectMember(cri);
    		mnv.addObject("dataMap",dataMap);
    		mnv.addObject("projectId",projectId);
    	} catch (SQLException e) {
    		e.printStackTrace();
		}
    	
    	/*mnv.addObject("projectId",projectId);
    	mnv.addObject("member",member);*/
    	mnv.setViewName(url);
    	return mnv;
    }
    
    @RequestMapping(value="/member/regist", method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> postRegistProjectMember(ModelAndView mnv, ProTeamVO project, HttpSession session) {
    	ResponseEntity<Map<String, Boolean>> entity = null;
    	Map<String, Boolean> result = new HashMap<String, Boolean>();

    	try {
    		pmsDashBoardService.registNewProjectMember(project, session);
    		result.put("result", true);
    	} catch (SQLException e) {
    		result.put("result", false);
		}
    	entity = new ResponseEntity<Map<String,Boolean>>(result,HttpStatus.OK);
    	return entity;
    }
    
    @RequestMapping(value="/closingSchedule/list", method=RequestMethod.GET)
    public ModelAndView getClosingScheduleList(ModelAndView mnv, int projectId) {
    	String url = "pms/dashboard/personalSchedule_closingList";
    	
    	List<PersonalScheduleVO> schedule = null;
    	try {
    		schedule = pmsDashBoardService.getClosingScheduleByProjectId(projectId);
    	} catch (SQLException e) {
    		e.printStackTrace();
		}
    	
    	mnv.addObject("schedule", schedule);
    	mnv.setViewName(url);
    	return mnv;
    }
    
    /**
     * @author 김령환
     * @category 프로젝트 완료
     * @param projectId
     * @breif 관리자가 해당 버튼을 클릭하여 프로젝트를 완료상태로 변경. 변경이 완료되면 전체 팀원들에게 알림을 보낸다.
     */
    @RequestMapping(value="project/complete")
    public ModelAndView completeProject(ModelAndView mnv, HttpSession session, int projectId) throws Exception{
    	String url = "pms/dashboard/projectComplete";
    	
    	MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("loginUser", loginUser);
    	map.put("projectId", projectId);
    	
    	projectCommonService.modifyProjectComplete(map);
    	
    	
    	return mnv;
    }
    
}
