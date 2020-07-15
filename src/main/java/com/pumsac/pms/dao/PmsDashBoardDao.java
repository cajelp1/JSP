package com.pumsac.pms.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.pumsac.command.SearchCriteria;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.OfficialScheduleVO;
import com.pumsac.vo.PersonalScheduleVO;
import com.pumsac.vo.ProTeamVO;
import com.pumsac.vo.ProjectVO;

public interface PmsDashBoardDao {

     List<Map<String,String>>selectProTeamNatureJoinProMasterByProjectIdOrderByCondition(SearchCriteria cri)throws  SQLException;

	ProjectVO selectProjectInfoByProjcetId(int projectId) throws SQLException;
	/**
	 * 프로젝트의 팀 일정을 조회하는 메소드
	 * @param projectId
	 * @return List<OfficialScheduleVO>
	 */
	List<OfficialScheduleVO> selectProjectOfficialSchedule(int projectId) throws SQLException;
	
	/**
	 * 프로젝트의 팀 멤버를 조회하는 메소드
	 * @param projectId
	 * @return List<ProTeamVO>
	 */
	List<ProTeamVO> selectProjectTeamMember(int projectId) throws SQLException;

	/**
	 * 프로젝트의 전체 업무를 조회하는 메소드
	 * @param projectId
	 * @return List<PersonalScheduleVO>
	 */
	List<PersonalScheduleVO> selectRecentPersonalSchedule(int projectId) throws SQLException;

	/**
	 * 프로젝트의 전체 업무 갯수를 조회하는 메소드
	 * @param projectId
	 * @return int
	 */
	int selectCountPersonalSchedule(int projectId) throws SQLException;
	
	/**
	 * 프로젝트의 업무 전체 진행률을 평균으로 낸 수치를 조회하는 메소드
	 * @param projectId
	 * @return float
	 */
	float selectTotalProcessPercent(int projectId) throws SQLException;

	/**
	 * 프로젝트의 마감이 가까운 업무를 조회하는 메소드
	 * @param projectId
	 * @return
	 */
	List<PersonalScheduleVO> selectclosingPersonalSchedule(int projectId) throws SQLException;

	/**
	 * 프로젝트 멤버별 진행률을 조회하는 메소드
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	List<PersonalScheduleVO> selectMemberProcessPercent(int projectId) throws SQLException;

	/**
	 * 업무 완료 대기중인 List
	 * @param projectId
	 * @return
	 */
	List<PersonalScheduleVO> selectWaitingCompletedScheduleList(int projectId) throws SQLException;

	/**
	 * 프로젝트에 참여중인 Member List
	 * @param cri
	 * @return
	 */
	List<MemberVO> selectProjectMember(SearchCriteria cri) throws SQLException;

	/**
	 * 프로젝트에 참여중인 Member 한명을 내보내는 메소드
	 * @param project
	 * @throws SQLException
	 */
	void deleteProejectMember(ProTeamVO project) throws SQLException;
	
	/**
	 * 프로젝트에 아직 참여중이 아닌 전체 Member를 조회하는 메소드
	 * @param SearchCriteria
	 * @return
	 * @throws SQLException
	 */
	List<MemberVO> selectRegistrableProjectMember(SearchCriteria cri) throws SQLException;

	/**
	 * 프로젝트에 새로운 멤버를 추가하는 메소드
	 * @param project
	 * @throws SQLException
	 */
	void insertNewProjectMember(ProTeamVO project) throws SQLException;

	/**
	 * 프로젝트의 관리자를 조회하는 메소드
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	String selectProjectManger(int projectId) throws SQLException;
	
	List<PersonalScheduleVO> selectPersonalSchedule(PersonalScheduleVO vo) throws SQLException;

	/**
	 * 참여 가능한 프로젝트 멤버 수 카운트
	 * @param cri
	 * @return
	 */
	int selectCountProjectMemeberByProjectId(SearchCriteria cri);

	/**
	 * 현재 참여중인 프로젝트 멤버 수 카운트
	 * @param projectId
	 * @return
	 */
	int selectCurrentCountProjectMemeberByProjectId(int projectId);
	
}
