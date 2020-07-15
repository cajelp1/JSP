package com.pumsac.pms.service;

import java.sql.SQLException;
import java.util.Map;

import com.pumsac.vo.OfficialScheduleVO;
import com.pumsac.vo.PlanVO;

public interface PmsOfficialScheduleService {

	/**
	 * @내용 해당 프로젝트의 공식일정들을 가져온다.
	 * @return List<OfficialScheduleVO> 
	 */
	Map<String, Object> getOfficalScheduleList(Map<String, Object> map) throws SQLException;
	
	/**
	 * @내용 해당 프로젝트의 공식일정을 등록한다.
	 */
	void registOffcialSchedule(PlanVO plan) throws SQLException;

	PlanVO getDetailOfficialSchedule(int no) throws SQLException;

	void modifyOffcialSchedule(PlanVO plan) throws SQLException;
	
	void removeOfficalSchedule(int no) throws SQLException;

	

	
}
