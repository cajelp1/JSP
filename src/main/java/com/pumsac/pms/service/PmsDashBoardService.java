package com.pumsac.pms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.pumsac.command.SearchCriteria;
import com.pumsac.vo.PersonalScheduleVO;
import com.pumsac.vo.ProTeamVO;

public interface PmsDashBoardService {

    List<Map<String, String>> getProjectListOrderByCondition(SearchCriteria criteria) throws SQLException;

    Map<String,Object> getProjectInfoByProjectId(int projectId) throws SQLException;

	Map<String, Object> getProjectMemberByProjectId(SearchCriteria cri) throws SQLException;

	List<PersonalScheduleVO> getCompletedPersonalSchedule(int projectId) throws SQLException;

	void removeProjectMember(String name, int projectId, HttpSession session) throws SQLException;

	void changeProjectManager(String email, int projectId, HttpSession session) throws SQLException;

	void approveCompletedSchedule(PersonalScheduleVO schedule) throws SQLException;

	void requestCompletedSchedule(PersonalScheduleVO schedule) throws SQLException;

	Map<String, Object> getRegistrableProjectMember(SearchCriteria cri) throws SQLException;

	void registNewProjectMember(ProTeamVO project, HttpSession session) throws SQLException;

	List<PersonalScheduleVO> getClosingScheduleByProjectId(int projectId) throws SQLException;
	
	List<PersonalScheduleVO> getPersonalTasks(int projectId, String memberId) throws SQLException;
}
