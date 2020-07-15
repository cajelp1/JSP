package com.pumsac.pms.service;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumsac.command.PageMaker;
import com.pumsac.command.SearchCriteria;
import com.pumsac.dao.MemberDao;
import com.pumsac.pms.dao.PmsNotHistoryDao;
import com.pumsac.pms.dao.PmsOfficialScheduleDao;
import com.pumsac.pms.dao.ProjectCommonDao;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.NotHistoryVO;
import com.pumsac.vo.PlanVO;
import com.pumsac.vo.ProMasterVO;
import com.pumsac.vo.ProTeamVO;

@Service("officalScheduleService")
public class PmsOfficalScheduleServiceImpl implements PmsOfficialScheduleService {

	@Autowired
	private PmsOfficialScheduleDao pmsOfficialScheduleDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private ProjectCommonDao projectCommonDao;
	
	@Autowired
	private PmsNotHistoryDao pmsNotHistoryDao;
	
	private Date endDatePlus(PlanVO officialSchedule) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(officialSchedule.getEndDate());
		cal.add(Calendar.HOUR, +23);
		return cal.getTime();
	}
	
	private void sendAlarm(int no, PlanVO officialScheduleVO, String title) throws SQLException {
		List<ProTeamVO> teamList = projectCommonDao.selectProjectTeamMember(officialScheduleVO.getProjectId());
		ProMasterVO master = projectCommonDao.selectProjectMaster(officialScheduleVO.getProjectId());
		
		NotHistoryVO notHistoryVO = new NotHistoryVO();
		notHistoryVO.setTitle(master.getProjectName() + " : " + title);
		notHistoryVO.setContent(officialScheduleVO.getContent());
		notHistoryVO.setProjectId(officialScheduleVO.getProjectId());
		notHistoryVO.setReadyn("n");
		notHistoryVO.setSender(officialScheduleVO.getMemberId());
		notHistoryVO.setUrl("/pms/team/officialSchedule/detail?no="+no + "&projectId="+ officialScheduleVO.getProjectId());
		notHistoryVO.setType(911);
		for (ProTeamVO vo : teamList) {
			notHistoryVO.setNo(pmsNotHistoryDao.selectSeq());
			notHistoryVO.setReceiver(vo.getMemberId());
			pmsNotHistoryDao.InsertNotHistory(notHistoryVO);
		}
	}
	
	@Override
	public Map<String, Object> getOfficalScheduleList(Map<String, Object> map) throws SQLException {
		int projectId = (int)map.get("projectId");
		SearchCriteria cri = (SearchCriteria)map.get("cri");
		
		
		int count = (int)pmsOfficialScheduleDao.selectOfficalScheduleCount(projectId);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(count);
		
		List<PlanVO> officalScheduleList = pmsOfficialScheduleDao.selectOfficalScheduleList(map);
		for (PlanVO ov : officalScheduleList) {
			MemberVO mem = memberDao.selectMemberByEmail(ov.getMemberId());
			ov.setMemberName(mem.getName());
			ov.setMemberNickName(mem.getNickname());
		}
		
		
		Map<String,Object> dataMap=new HashMap<String,Object>();
		dataMap.put("officalScheduleList", officalScheduleList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}

	@Override
	public void registOffcialSchedule(PlanVO officialSchedule) throws SQLException {
		officialSchedule.setEndDate(endDatePlus(officialSchedule));
		
		int no = pmsOfficialScheduleDao.selectSeqToOfficialSchedual();
		officialSchedule.setNo(no);
		
		pmsOfficialScheduleDao.insertOffcialSchedule(officialSchedule);
		
		sendAlarm(no, officialSchedule, "공식일정이 추가되었습니다.");
	}

	@Override
	public PlanVO getDetailOfficialSchedule(int no) throws SQLException {
		PlanVO ov = pmsOfficialScheduleDao.selectDetailOfficialSchedule(no);
		MemberVO mem = memberDao.selectMemberByEmail(ov.getMemberId());
		ov.setMemberName(mem.getName());
		ov.setMemberNickName(mem.getNickname());
		return ov;
	}

	@Override
	public void modifyOffcialSchedule(PlanVO officialScheduleVO) throws SQLException {
		officialScheduleVO.setEndDate(endDatePlus(officialScheduleVO));
		
		sendAlarm(officialScheduleVO.getNo(), officialScheduleVO, "공식일정이 수정되었습니다.");
		
		pmsOfficialScheduleDao.updateOffcialSchedule(officialScheduleVO);
	}
	
	@Override
	public void removeOfficalSchedule(int no) throws SQLException {
		pmsOfficialScheduleDao.deleteOfficialSchedule(no);
	}
}
