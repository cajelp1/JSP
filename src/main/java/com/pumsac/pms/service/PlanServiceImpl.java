package com.pumsac.pms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumsac.pms.dao.PlanDao;
import com.pumsac.pms.dao.PmsNotHistoryDao;
import com.pumsac.pms.dao.ProjectCommonDao;
import com.pumsac.vo.NotHistoryVO;
import com.pumsac.vo.PlanVO;
import com.pumsac.vo.ProMasterVO;
import com.pumsac.vo.ProTeamVO;
@Service("planService")
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanDao planDao;
	
	@Autowired
	private ProjectCommonDao projectCommonDao;
	
	@Autowired
	private PmsNotHistoryDao pmsNotHistoryDao;
	
//	private Date endDatePlus(PlanVO plan) throws ParseException {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(plan.getEndDate()) );
//		cal.add(Calendar.HOUR, +23);
//		return cal.getTime();
//	}
	
	private void sendAlarmToAll(int no, PlanVO plan) throws SQLException {
		List<ProTeamVO> teamList = projectCommonDao.selectProjectTeamMember(plan.getProjectId());
		ProMasterVO master = projectCommonDao.selectProjectMaster(plan.getProjectId());
		
		NotHistoryVO notHistoryVO = new NotHistoryVO();
		notHistoryVO.setTitle(master.getProjectName() + " : " + plan.getTitle());
		notHistoryVO.setContent(plan.getContent());
		notHistoryVO.setProjectId(plan.getProjectId());
		notHistoryVO.setReadyn("n");
		notHistoryVO.setSender(plan.getMemberId());
		notHistoryVO.setUrl("/pms/admin/team/detail?no="+no + "&projectId="+ plan.getProjectId());
		notHistoryVO.setType(911);
		for (ProTeamVO vo : teamList) {
			notHistoryVO.setNo(pmsNotHistoryDao.selectSeq());
			notHistoryVO.setReceiver(vo.getMemberId());
			pmsNotHistoryDao.InsertNotHistory(notHistoryVO);
		}
	}
	
	@Override
	public void registPersonalPlan(PlanVO plan) throws SQLException {
		int no = planDao.selectPlanSeq();
		plan.setNo(no);
		plan.setCode("1");
		planDao.insertPersonalPlan(plan);
		
		
	}
	
	@Override
	public void registTeamPlan(PlanVO plan) throws SQLException {
		int no = planDao.selectPlanSeq();
		plan.setNo(no);
		plan.setCode("2");
		planDao.insertPersonalPlan(plan);
		
		sendAlarmToAll(no, plan);
	}

	@Override
	public void modifyPlan(PlanVO plan) throws SQLException {
		planDao.updatePlan(plan);
		int no = plan.getNo();
		sendAlarmToAll(no, plan);
	}

	@Override
	public List<PlanVO> getPersonalPlan(PlanVO plan) throws SQLException {
		plan.setCode("1");
		return planDao.selectPersonalPlan(plan);
	}

	@Override
	public List<PlanVO> getTeamPlan(int projectId, String code) throws SQLException {
		PlanVO plan = new PlanVO();
		plan.setProjectId(projectId);
		plan.setCode("2");
		List<PlanVO> list = planDao.selectTeamPlan(plan);
//		Map<String,Object> dataMap=new HashMap<String,Object>();
//		dataMap.put("planList", list);
		return list;
	}

	@Override
	public void removePlan(int no) throws SQLException {
		planDao.deletePlan(no);
	}

	@Override
	public PlanVO getTeamPlanDetail(int no) throws SQLException {
		return planDao.selectPlanDetail(no);
	}
}
