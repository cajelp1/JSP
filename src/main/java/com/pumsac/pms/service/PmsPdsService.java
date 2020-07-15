package com.pumsac.pms.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.pumsac.vo.ProBoardMasterVO;
import com.pumsac.vo.ProBoardVO;

public interface PmsPdsService {

	public void registPds(ProBoardVO board, ProBoardMasterVO boardMaster) throws SQLException;

	public List<ProBoardVO> getPdsList(Map<String, Object> map) throws SQLException;

	public Map<String, Object> getDetail(int no) throws SQLException;

	public void modifyPds(ProBoardVO board, ProBoardMasterVO boardMaster, List<Integer> deleteFileNoList) throws SQLException, IOException;

	public void removePds(int no) throws SQLException, IOException;

	public ProBoardMasterVO getBoardMasterInfo(ProBoardMasterVO master) throws SQLException;
	
	public List<ProBoardMasterVO> getBoadMasterList(int projectId) throws SQLException;
}
