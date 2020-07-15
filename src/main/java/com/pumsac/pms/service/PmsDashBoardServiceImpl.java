package com.pumsac.pms.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumsac.command.PageMaker;
import com.pumsac.command.SearchCriteria;
import com.pumsac.dao.MemberDao;
import com.pumsac.pms.dao.PlanDao;
import com.pumsac.pms.dao.PmsDashBoardDao;
import com.pumsac.pms.dao.PmsNotHistoryDao;
import com.pumsac.pms.dao.PmsPersonalScheduleDao;
import com.pumsac.pms.dao.ProjectCommonDao;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.NotHistoryVO;
import com.pumsac.vo.PersonalScheduleVO;
import com.pumsac.vo.PlanVO;
import com.pumsac.vo.ProMasterVO;
import com.pumsac.vo.ProTeamVO;
import com.pumsac.vo.ProjectVO;

import net.sf.json.JSONArray;

@Service("pmsDashBoardService")
public class PmsDashBoardServiceImpl implements PmsDashBoardService {
    
	@Autowired
    PmsDashBoardDao pmsDashBoardDao;

    @Autowired
	private ProjectCommonDao projectCommonDao;
    
    @Autowired
    private PmsPersonalScheduleDao personalSchedule;
    
    @Autowired
    private PmsNotHistoryDao pmsNotHistoryDao;
    
    @Autowired
    private MemberDao memberDao;
    
    @Autowired
	private PlanDao planDao;
    
    @Override
    public List<Map<String, String>> getProjectListOrderByCondition(SearchCriteria cri) throws SQLException {

        List<Map<String, String>> list = pmsDashBoardDao.selectProTeamNatureJoinProMasterByProjectIdOrderByCondition(cri);
        return list;

    }


	@Override
	public Map<String,Object> getProjectInfoByProjectId(int projectId) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		ProjectVO project = pmsDashBoardDao.selectProjectInfoByProjcetId(projectId);
		//팀 일정 List
		PlanVO plan = new PlanVO();
		plan.setProjectId(projectId);
		plan.setCode("2");
		List<PlanVO> teamSchedule = planDao.selectTeamPlan(plan);
		
		// 프로젝트 참여자 목록
		List<ProTeamVO> team = pmsDashBoardDao.selectProjectTeamMember(projectId);
		
		// 최근에 등록된 업무 List
		List<PersonalScheduleVO> recentSchedule = pmsDashBoardDao.selectRecentPersonalSchedule(projectId);
		
		// 마감 임박 업무 List
		List<PersonalScheduleVO> closingSchedule = pmsDashBoardDao.selectclosingPersonalSchedule(projectId);
		
		// 전체 진행 상황 퍼센트
		float percent = pmsDashBoardDao.selectTotalProcessPercent(projectId);
		
		// 멤버별 평균 진행 상황
		List<PersonalScheduleVO> memberPercent = pmsDashBoardDao.selectMemberProcessPercent(projectId);
	    
		JSONArray jsonPercentList = JSONArray.fromObject(memberPercent);
				
		// 업무 완료 대기중인 List
		List<PersonalScheduleVO> waitingCompletedScehdule = pmsDashBoardDao.selectWaitingCompletedScheduleList(projectId);
		
		dataMap.put("percent", percent);
		dataMap.put("project", project);
		dataMap.put("teamSchedule", teamSchedule);
		dataMap.put("team", team);
		dataMap.put("recentSchedule", recentSchedule);
		dataMap.put("closingSchedule", closingSchedule);
		dataMap.put("memberPercent", jsonPercentList);
		dataMap.put("waitingCompletedScehdule", waitingCompletedScehdule);
		
		return dataMap;
	}


	@Override
	public Map<String, Object> getProjectMemberByProjectId(SearchCriteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		//참여자 목록
		List<MemberVO> projectMember = pmsDashBoardDao.selectProjectMember(cri);
		
		//프로젝트 정보
		ProjectVO project = pmsDashBoardDao.selectProjectInfoByProjcetId(cri.getProjectId());

		int totalCount = pmsDashBoardDao.selectCurrentCountProjectMemeberByProjectId(cri.getProjectId());

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		
		dataMap.put("pageMaker", pageMaker);
		dataMap.put("projectMember", projectMember);
		dataMap.put("project", project);
		dataMap.put("projectId", cri.getProjectId());
		
		return dataMap;
	}


	@Override
	public List<PersonalScheduleVO> getCompletedPersonalSchedule(int projectId) throws SQLException {
		// 업무 완료 대기중인 List
		List<PersonalScheduleVO> waitingCompletedScehdule = pmsDashBoardDao.selectWaitingCompletedScheduleList(projectId);
		
		return waitingCompletedScehdule;
	}


	@Override
	public void removeProjectMember(String email, int projectId, HttpSession session) throws SQLException {
		ProTeamVO project = new ProTeamVO();
		project.setMemberId(email);
		project.setProjectId(projectId);
		project.setJob("120");
		
		pmsDashBoardDao.deleteProejectMember(project);
		
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		ProMasterVO master = projectCommonDao.selectProjectMaster(project.getProjectId());
		int seq = pmsNotHistoryDao.selectSeq();
		NotHistoryVO notHistoryVO = new NotHistoryVO();
		notHistoryVO.setNo(seq);
		notHistoryVO.setTitle(master.getProjectName() + " : 프로젝트에서 제외되셨습니다.");
		notHistoryVO.setContent(project.getMemberNickName() + "님이" + project.getProjectName() + "에서 제외되셨습니다.");
		notHistoryVO.setProjectId(project.getProjectId());
		notHistoryVO.setReadyn("n");
		notHistoryVO.setSender(loginUser.getEmail());
		notHistoryVO.setUrl("/pms/notHistory/detail?no="+seq);
		notHistoryVO.setType(912);
		notHistoryVO.setReceiver(project.getMemberId());
		pmsNotHistoryDao.InsertNotHistory(notHistoryVO);
	}


	@Override
	public void changeProjectManager(String email, int projectId, HttpSession session) throws SQLException {
		newMangerInsert(email, projectId);
		managerToUser(email, projectId, session);
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		String register = loginUser.getEmail();
		ProMasterVO pro = new ProMasterVO();
		pro.setProjectId(projectId);
		pro.setRegister(register);
		projectCommonDao.updateProjectRegister(pro);
		
		
		// 알림보내기
		List<ProTeamVO> teamList = projectCommonDao.selectProjectTeamMember(projectId);
		ProMasterVO master = projectCommonDao.selectProjectMaster(projectId);
		MemberVO member = memberDao.selectMemberByEmail(email);
		MemberVO manager = (MemberVO) session.getAttribute("loginUser");
		NotHistoryVO notHistoryVO = new NotHistoryVO();
		notHistoryVO.setTitle(member.getEmail() + "님이 매니저가 되셨습니다.");
		notHistoryVO.setContent(manager.getNickname()+"님이 "+ member.getEmail()+ "에게 매니저 권한을 양도하셨습니다.");
		notHistoryVO.setProjectId(projectId);
		notHistoryVO.setReadyn("n");
		notHistoryVO.setSender(manager.getEmail());
		notHistoryVO.setType(910);
		for (ProTeamVO vo : teamList) {
			int seq = pmsNotHistoryDao.selectSeq();
			notHistoryVO.setNo(seq);
			notHistoryVO.setUrl("/pms/notHistory/detail?no="+seq);
			notHistoryVO.setReceiver(vo.getMemberId());
			pmsNotHistoryDao.InsertNotHistory(notHistoryVO);
		}
		
	}


	private void managerToUser(String email, int projectId, HttpSession session) throws SQLException {
		ProTeamVO project = newMangerInsert(email, projectId);
		//기존 매니저 사용자로 변경
		MemberVO admin = (MemberVO) session.getAttribute("loginUser");
		project.setMemberId(admin.getEmail());
		projectCommonDao.updateProjectManagertoUser(project);
	}


	private ProTeamVO newMangerInsert(String email, int projectId) throws SQLException {
		//새로운사람에게 매니저 양도
		ProTeamVO project = new ProTeamVO();
		project.setProjectId(projectId);
		project.setMemberId(email);
		projectCommonDao.updateProjectManager(project);
		return project;
	}


	@Override
	public void approveCompletedSchedule(PersonalScheduleVO schedule) throws SQLException {
		schedule.setApprovalYn("y");
		schedule.setProcessStaues("701");
		personalSchedule.updateCompleteScheduleStatus(schedule);
		
	}


	@Override
	public void requestCompletedSchedule(PersonalScheduleVO schedule) throws SQLException {
		schedule.setApprovalYn("n");
		schedule.setProcessStaues("721");
		personalSchedule.updatePersonalScheduleStatus(schedule);
	}


	@Override
	public Map<String, Object> getRegistrableProjectMember(SearchCriteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		List<MemberVO> list = pmsDashBoardDao.selectRegistrableProjectMember(cri);

		int totalCount = pmsDashBoardDao.selectCountProjectMemeberByProjectId(cri);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		dataMap.put("member", list);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}


	@Override
	public void registNewProjectMember(ProTeamVO project, HttpSession session) throws SQLException {		
		pmsDashBoardDao.insertNewProjectMember(project);		
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		ProMasterVO master = projectCommonDao.selectProjectMaster(project.getProjectId());
		int seq = pmsNotHistoryDao.selectSeq();
		NotHistoryVO notHistoryVO = new NotHistoryVO();
		notHistoryVO.setNo(seq);
		notHistoryVO.setTitle(master.getProjectName() + " : 프로젝트에 초대되었습니다.");
		notHistoryVO.setContent(master.getProjectName() + "에 팀원으로 초대되었습니다.");
		notHistoryVO.setProjectId(project.getProjectId());
		notHistoryVO.setReadyn("n");
		notHistoryVO.setSender(loginUser.getEmail());
		notHistoryVO.setUrl("/pms/user?projectId="+project.getProjectId());
		notHistoryVO.setType(912);
		notHistoryVO.setReceiver(project.getMemberId());
		pmsNotHistoryDao.InsertNotHistory(notHistoryVO);
	}


	@Override
	public List<PersonalScheduleVO> getClosingScheduleByProjectId(int projectId) throws SQLException {
		return pmsDashBoardDao.selectclosingPersonalSchedule(projectId);
	}


	@Override
	public List<PersonalScheduleVO> getPersonalTasks(int projectId, String memberId) throws SQLException {
		List<PersonalScheduleVO> task = null;
		PersonalScheduleVO vo = new PersonalScheduleVO();
		vo.setProjectId(projectId);
		vo.setMemberId(memberId);
		task = pmsDashBoardDao.selectPersonalSchedule(vo);
		return task;
	}
	
	
	
	
}
