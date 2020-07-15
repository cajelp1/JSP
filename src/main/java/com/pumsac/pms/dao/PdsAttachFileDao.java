package com.pumsac.pms.dao;

import java.sql.SQLException;
import java.util.List;

import com.pumsac.vo.AttachFileVO;

public interface PdsAttachFileDao {

	void insertAttachFileToPds(AttachFileVO vo) throws SQLException;

	List<AttachFileVO> selectPdsFileList(int pno) throws SQLException;

	void removePdsFile(int fileNo) throws SQLException;
	
}
