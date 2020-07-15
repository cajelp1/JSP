package com.pumsac.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pumsac.vo.AttachFileVO;
import com.pumsac.vo.ProBoardMasterVO;
import com.pumsac.vo.ProMasterVO;
import com.pumsac.vo.ProTeamVO;

@Repository("projectComonDao")
public class ProjectCommonDaoImpl implements ProjectCommonDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int selectProejctIdForDual() throws SQLException {
		return sqlSession.selectOne("projectCommon-mapper.selectProejctIdForDual");
	}

	@Override
	public void insertProjectToProMasterTable(ProMasterVO proMasterVO) throws SQLException {
		sqlSession.update("projectCommon-mapper.insertProjectToProMasterTable", proMasterVO);
	}

	@Override
	public void insertProjectMemberToProTeamTable(ProTeamVO proTeamVO) throws SQLException {
		sqlSession.update("projectCommon-mapper.insertProjectMemberToProTeamTable", proTeamVO);
	}

	@Override
	public List<ProTeamVO> selectProjectList(String memberEmail) throws SQLException {
		return sqlSession.selectList("projectCommon-mapper.selectProjectList", memberEmail);
	}
	
	@Override
	public void disableProject(int projectId) throws SQLException {
		sqlSession.update("projectCommon-mapper.disableProject", projectId);
	}

	@Override
	public void enableProject(int projectId) throws SQLException {
		sqlSession.update("projectCommon-mapper.enableProject", projectId);
	}

	@Override
	public ProMasterVO selectProjectMaster(int projectId) throws SQLException {
		return sqlSession.selectOne("projectCommon-mapper.selectProjectMaster", projectId);
	}

	@Override
	public List<ProTeamVO> selectProjectTeamMember(int projectId) throws SQLException {
		return sqlSession.selectList("projectCommon-mapper.selectProjectTeamMember", projectId);
	}

	@Override
	public ProBoardMasterVO selectBoardName(ProBoardMasterVO boardMaster) throws SQLException {
		return sqlSession.selectOne("projectCommon-mapper.selectBoardName", boardMaster);
	}

	@Override
	public AttachFileVO selectFileInfo(int no) throws SQLException {
		return sqlSession.selectOne("projectCommon-mapper.selectFileInfo", no);
	}

	@Override
	public void updateProjectManager(ProTeamVO project) throws SQLException {
		sqlSession.update("projectCommon-mapper.updateProjectManager", project);
	}

	@Override
	public void updateProjectManagertoUser(ProTeamVO project) throws SQLException {
		sqlSession.update("projectCommon-mapper.updateProjectManagertoUser", project);
	}

	@Override
	public void updateProjectRegister(ProMasterVO pro) {
		sqlSession.update("projectCommon-mapper.updateProjectRegister", pro);
	}

	@Override
	public void insertProjectTeamTable(ProTeamVO team) {
		sqlSession.update("projectCommon-mapper.insertProjectTeamTable",team);
	}

	@Override
	public void updateProjectComplete(int projectId) {
		sqlSession.update("projectCommon-mapper.updateProjectComplete", projectId);
	}

	@Override
	public void insert_pro_board(ProBoardMasterVO board) throws SQLException {
		sqlSession.update("projectCommon-mapper.insert_pro_board", board);
		
	}
}
