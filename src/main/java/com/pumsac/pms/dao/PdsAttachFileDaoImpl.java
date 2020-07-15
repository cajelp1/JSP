package com.pumsac.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pumsac.vo.AttachFileVO;

@Repository("pdsAttachFileDao")
public class PdsAttachFileDaoImpl implements PdsAttachFileDao{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertAttachFileToPds(AttachFileVO vo) throws SQLException {
		sqlSession.update("pmsPdsAttachFile-mapper.insertAttachFileToPds", vo);
	}

	@Override
	public List<AttachFileVO> selectPdsFileList(int pno) throws SQLException {
		return sqlSession.selectList("pmsPdsAttachFile-mapper.selectPdsFileList", pno);
	}

	@Override
	public void removePdsFile(int fileNo) throws SQLException {
		sqlSession.update("pmsPdsAttachFile-mapper.removePdsFile", fileNo);
	}
}