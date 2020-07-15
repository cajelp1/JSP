package com.pumsac.pms.service;

import com.pumsac.vo.PersonalScheduleVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PmsPersonalScheduleService {

	Map<String, Object> getPersonalScheduleAllListForAdmin(Map<String, Object> map) throws SQLException;
	
	Map<String, Object> getPersonalScheduleList(Map<String, Object> map) throws SQLException;

	void registPersonalSchedule(PersonalScheduleVO personalScheduleVO) throws SQLException;

	PersonalScheduleVO getDetailPersonalSchedule(int no) throws SQLException;

	void modifyPersonalScheduleByUser(PersonalScheduleVO personalScheduleVO) throws SQLException;

	void modifyPersonalScheduleByAdmin(PersonalScheduleVO personalScheduleVO) throws SQLException;

	void removeTask(int no) throws SQLException;

	List<PersonalScheduleVO> getTaskForProjectManager(PersonalScheduleVO paramTask) throws SQLException;

}
