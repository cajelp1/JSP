package com.pumsac.pms.dao;

import com.pumsac.vo.ProBoardMasterVO;
import com.pumsac.vo.ProBoardVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PmsPdsDao {

	public int selectNextProBoardSeq() throws SQLException;
	
	public void insertPds(ProBoardVO board) throws SQLException;

	public List<ProBoardVO> selectPdsList(Map<String, Object> map) throws SQLException;

	public void updatePdsViewCnt(int no) throws SQLException;
	
	public ProBoardVO selectPdsDetail(int no) throws SQLException;

	public void updatePds(ProBoardVO board) throws SQLException;

	public void removePds(int no) throws SQLException;
	
	//해당 프로젝트의 게시판들을 리스트로 가져옴
	public List<ProBoardMasterVO> selectBoardMasterList(int projectId) throws SQLException;
	
}
