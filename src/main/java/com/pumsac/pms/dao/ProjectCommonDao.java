package com.pumsac.pms.dao;

import com.pumsac.vo.AttachFileVO;
import com.pumsac.vo.ProBoardMasterVO;
import com.pumsac.vo.ProMasterVO;
import com.pumsac.vo.ProTeamVO;

import java.sql.SQLException;
import java.util.List;

public interface ProjectCommonDao {
	
	/**
	 * @내용 : 시퀀스 값을 가져온다.
	 */
	int selectProejctIdForDual() throws SQLException;
	
	/**
	 * @내용 : proMaster에 내용을 저장한다.
	 */
	void insertProjectToProMasterTable(ProMasterVO proMasterVO) throws SQLException;
	
	/**
	 * @내용 : proTeam에 내용을 저장한다.
	 */
	void insertProjectMemberToProTeamTable(ProTeamVO proTeamVO) throws SQLException;

	/**
	 * @내용 : 로그인한 사람의 프로젝트를 조회한다.
	 */
	List<ProTeamVO> selectProjectList(String memberEmail) throws SQLException;
	
	/**
	 * @내용 프로젝트 비활성화
	 */
	void disableProject(int projectId) throws SQLException;
	
	/**
	 * @내용 프로젝트 활성화
	 */
	void enableProject(int projectId) throws SQLException;
	
	/**
	 * @내용 프로젝트에 대한 정보 조회 pro_master 테이블
	 */
	ProMasterVO selectProjectMaster(int no) throws SQLException;
	
	/**
	 * @param projectId, memberId를 이용
	 * @내용 해당 프로젝트에 속한 멤버들을 조회한다. pro_team 테이블
	 */
	List<ProTeamVO> selectProjectTeamMember(int projectId) throws SQLException;

	/**
	 * @내용 : 해당 프로젝트게시판의 boardName을 가져온다.
	 * @param boardType, projectId
	 * @return String
	 */
	public ProBoardMasterVO selectBoardName(ProBoardMasterVO boardMaster) throws SQLException;

	/**
	 * @내용 : 파일 PK를 받아서 해당 파일의 정보를 가져온다.
	 * @param no
	 * @return
	 * @throws SQLException
	 */
	AttachFileVO selectFileInfo(int no) throws SQLException;

	/**
	 * @내용 : 기존 참여자를 프로젝트 관리자로 변경
	 * @param project
	 * @throws SQLException
	 */
	void updateProjectManager(ProTeamVO project) throws SQLException;

	/**
	 * 기존 프로젝트 관리자를 일반 사용자로 변경
	 * @param project
	 */
	void updateProjectManagertoUser(ProTeamVO project) throws SQLException;

	/**
	 * PRO_MASTER table의 register를 변경
	 * @param pro
	 */
	void updateProjectRegister(ProMasterVO pro);

	/**
	 * Pro_Team table Merge Into Insert
	 * @param team
	 */
	void insertProjectTeamTable(ProTeamVO team);

	/**
	 * @내용 : 프로젝트를 완료상태로 변경
	 * @param projectId
	 */
	void updateProjectComplete(int projectId);
	/**
	 * 프로젝트 생성시 자료실 혹은 공지사항 테이블 생성을 위해 pro_boardmaster 테이블에 insert
	 */
	void insert_pro_board(ProBoardMasterVO board) throws SQLException;
}
