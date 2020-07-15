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
import com.pumsac.pms.dao.PmsPersonalScheduleDao;
import com.pumsac.pms.dao.ProjectCommonDao;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.NotHistoryVO;
import com.pumsac.vo.PersonalScheduleVO;
import com.pumsac.vo.ProMasterVO;

@Service("pmsPersonalScheduleService")
public class PmsPersonalScheduleServiceImpl implements PmsPersonalScheduleService{
	
	@Autowired
	private PmsPersonalScheduleDao pmsPersonalScheduleDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private ProjectCommonDao projectCommonDao;
	
	@Autowired
	private PmsNotHistoryDao pmsNotHistoryDao;

	private Date endDatePlus(PersonalScheduleVO personalScheduleVO) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(personalScheduleVO.getEndDate());
		cal.add(Calendar.HOUR, +23);
		return cal.getTime();
	}
	
	private void sendAlarm(int no, PersonalScheduleVO personalScheduleVO, String title) throws SQLException {
		ProMasterVO master = projectCommonDao.selectProjectMaster(personalScheduleVO.getProjectId());
		
		NotHistoryVO notHistoryVO = new NotHistoryVO();
		notHistoryVO.setNo(pmsNotHistoryDao.selectSeq());
		notHistoryVO.setTitle(master.getProjectName() + " : " + title);
		notHistoryVO.setContent(personalScheduleVO.getContent());
		notHistoryVO.setProjectId(personalScheduleVO.getProjectId());
		notHistoryVO.setReadyn("n");
		notHistoryVO.setSender(personalScheduleVO.getCharger());
		notHistoryVO.setUrl("/pms/admin/personalSchedule/detail?projectId="+personalScheduleVO.getProjectId() + "&no=" + no);
		notHistoryVO.setType(911);
		notHistoryVO.setReceiver(personalScheduleVO.getMemberId());
		pmsNotHistoryDao.InsertNotHistory(notHistoryVO);
	}
	
	@Override
	public Map<String, Object> getPersonalScheduleAllListForAdmin(Map<String, Object> map) throws SQLException {
		PersonalScheduleVO personalScheduleVO = (PersonalScheduleVO)map.get("personalScheduleVO");
		SearchCriteria cri = (SearchCriteria)map.get("cri");
		
		int count = (int)pmsPersonalScheduleDao.selectPersonalScheduleAllCountForAdmin(personalScheduleVO);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(count);
		
		List<PersonalScheduleVO> personalScheduleList = pmsPersonalScheduleDao.selectPersonalScheduleAllForAdmin(map);
		for (PersonalScheduleVO psv : personalScheduleList) {
			MemberVO mem = memberDao.selectMemberByEmail(psv.getCharger());
			psv.setChargerName(mem.getName());
			psv.setChargerNickName(mem.getNickname());
		}
		
		Map<String,Object> dataMap=new HashMap<String,Object>();
		dataMap.put("personalScheduleList", personalScheduleList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}
	
	@Override
	public Map<String, Object> getPersonalScheduleList(Map<String, Object> map) throws SQLException {
		PersonalScheduleVO personalScheduleVO = (PersonalScheduleVO)map.get("personalScheduleVO");
		SearchCriteria cri = (SearchCriteria)map.get("cri");
		
		int count = (int)pmsPersonalScheduleDao.selectPersonalScheduleCount(personalScheduleVO);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(count);
		
		List<PersonalScheduleVO> personalScheduleList = pmsPersonalScheduleDao.selectPersonalScheduleList(map);
		for (PersonalScheduleVO psv : personalScheduleList) {
			MemberVO mem = memberDao.selectMemberByEmail(psv.getCharger());
			psv.setChargerName(mem.getName());
			psv.setChargerNickName(mem.getNickname());
		}
		
		Map<String,Object> dataMap=new HashMap<String,Object>();
		dataMap.put("personalScheduleList", personalScheduleList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}

	@Override
	public void registPersonalSchedule(PersonalScheduleVO personalScheduleVO) throws SQLException {
		personalScheduleVO.setEndDate(endDatePlus(personalScheduleVO));
		
		String members = personalScheduleVO.getMemberId();
		String[] member = members.split(",");
		
		/**
		 * 여러명에게 업무를 배정할 경우 for문을 통해 seq와 memberId를 각각 세팅
		 */
		for(int i = 0; i < member.length; i++) {
			int no = pmsPersonalScheduleDao.selectSeqToPersonalSchedual();
			personalScheduleVO.setNo(no);
			personalScheduleVO.setMemberId(member[i]);
			pmsPersonalScheduleDao.insertPersonalSchedule(personalScheduleVO);
			sendAlarm(personalScheduleVO.getNo(), personalScheduleVO, "개인일정이 추가되었습니다.");
		}
		
	}

	@Override
	public PersonalScheduleVO getDetailPersonalSchedule(int no) throws SQLException{
		PersonalScheduleVO psv = pmsPersonalScheduleDao.selectDetailPersonalSchedule(no);
		MemberVO charger = memberDao.selectMemberByEmail(psv.getCharger());
		psv.setChargerName(charger.getName());
		psv.setChargerNickName(charger.getNickname());
		
		MemberVO member = memberDao.selectMemberByEmail(psv.getMemberId());
		psv.setMemberName(member.getName());
		psv.setMemberNickName(member.getNickname());
		
		return psv;
	}
	
	@Override
	public void modifyPersonalScheduleByUser(PersonalScheduleVO personalScheduleVO) throws SQLException {
		if (personalScheduleVO.getProcessPercent() == 100) {
			ProMasterVO master = projectCommonDao.selectProjectMaster(personalScheduleVO.getProjectId());
			
			NotHistoryVO notHistoryVO = new NotHistoryVO();
			notHistoryVO.setTitle(master.getProjectName() + " : 개인일정 완료요청이 왔습니다.");
			notHistoryVO.setNo(pmsNotHistoryDao.selectSeq());
			notHistoryVO.setContent(personalScheduleVO.getContent());
			notHistoryVO.setProjectId(personalScheduleVO.getProjectId());
			notHistoryVO.setReadyn("n");
			notHistoryVO.setSender(personalScheduleVO.getMemberId());
			notHistoryVO.setUrl("/pms/admin/personalSchedule/detail?projectId="+personalScheduleVO.getProjectId() + "&no=" + personalScheduleVO.getNo());
			notHistoryVO.setType(910);
			notHistoryVO.setReceiver(personalScheduleVO.getCharger());
			pmsNotHistoryDao.InsertNotHistory(notHistoryVO);
		} else {
			ProMasterVO master = projectCommonDao.selectProjectMaster(personalScheduleVO.getProjectId());
			
			NotHistoryVO notHistoryVO = new NotHistoryVO();
			notHistoryVO.setTitle(master.getProjectName() + " : 개인일정 재요청이 왔습니다.");
			notHistoryVO.setNo(pmsNotHistoryDao.selectSeq());
			notHistoryVO.setContent(personalScheduleVO.getContent());
			notHistoryVO.setProjectId(personalScheduleVO.getProjectId());
			notHistoryVO.setReadyn("n");
			notHistoryVO.setSender(personalScheduleVO.getMemberId());
			notHistoryVO.setUrl("/pms/admin/personalSchedule/detail?projectId="+personalScheduleVO.getProjectId() + "&no=" + personalScheduleVO.getNo());
			notHistoryVO.setType(910);
			notHistoryVO.setReceiver(personalScheduleVO.getCharger());
			pmsNotHistoryDao.InsertNotHistory(notHistoryVO);
		}
		
		pmsPersonalScheduleDao.updatePersonalScheduleByUser(personalScheduleVO);
		
	}
	
	@Override
	public void modifyPersonalScheduleByAdmin(PersonalScheduleVO personalScheduleVO) throws SQLException {
		pmsPersonalScheduleDao.updatePersonalScheduleByAdmin(personalScheduleVO);
	}

	@Override
	public void removeTask(int no) throws SQLException {
		pmsPersonalScheduleDao.deleteTaskByAdmin(no);
		
	}

	@Override
	public List<PersonalScheduleVO> getTaskForProjectManager(PersonalScheduleVO paramTask) throws SQLException {
		return pmsPersonalScheduleDao.selectTaskForProjectManager(paramTask);
	}
}
