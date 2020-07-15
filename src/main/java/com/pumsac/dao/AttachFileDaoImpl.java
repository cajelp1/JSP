package com.pumsac.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pumsac.vo.AttachFileVO;

@Repository("attachFileDao")
public class AttachFileDaoImpl implements AttachFileDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void registMemberProfileImage(AttachFileVO vo) throws SQLException {
		
		sqlSession.update("attachFile-mapper.registProfileImage", vo);
	}

	@Override
	public int getAttachFileSeq() throws SQLException {
		return sqlSession.selectOne("attachFile-mapper.countAttachFileSeq");
	}

	@Override
	public void registSiteNoticeAttach(AttachFileVO vo) throws SQLException {
		sqlSession.update("attachFile-mapper.registSiteNoticeAttach", vo);
	}

	@Override
	public int getBoardCurrentSeq() throws SQLException {
		return sqlSession.selectOne("attachFile-mapper.getBoardCurrentSeq");
	}

	@Override
	public AttachFileVO getAttachFileBySno(int sno) throws SQLException {
		AttachFileVO attachVO = new AttachFileVO();
		attachVO = sqlSession.selectOne("attachFile-mapper.getAttachFileBySno", sno);
		return attachVO;
	}

	@Override
	public void attachFilePmsNoticBoard(AttachFileVO vo) throws SQLException {
		// TODO Auto-generated method stub
		sqlSession.update("attachFile-mapper.attachFilePmsNoticBoard", vo);
		
	}

	@Override
	public int proBoardCurrentSeq() throws SQLException {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("attachFile-mapper.proBoardCurrentSeq");
	}


	
	@Override
	public AttachFileVO getFileListAtPmsNoticBoardByPno(int pno) throws SQLException {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("attachFile-mapper.getFileListAtPmsNoticBoardByPno", pno);
	}

	@Override
	public AttachFileVO getFileListAtPmsNoticBoardByOriginFileName(String fileName) throws SQLException {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("attachFile-mapper.getFileListAtPmsNoticBoardByOriginFileName",fileName);
	}

	@Override
	public void modifyAttachFile(AttachFileVO vo) throws SQLException {
		sqlSession.selectOne("attachFile-mapper.modifyAttachFile", vo);
	}

	@Override
	public void registSiteNoticeAttachAtModify(AttachFileVO vo) {
		sqlSession.selectOne("attachFile-mapper.registSiteNoticeAttach", vo);
	}

	@Override
	public AttachFileVO selectAttachFileInfo(int fileNo) throws SQLException {
		return sqlSession.selectOne("attachFile-mapper.selectAttachFileInfo", fileNo);
	}

	@Override
	public void deleteFileInfo(int fileNo) throws SQLException {
		sqlSession.delete("attachFile-mapper.deleteFileInfo", fileNo);
	}

	@Override
	public AttachFileVO getMemberProfileImg(String email) throws SQLException {
		return sqlSession.selectOne("attachFile-mapper.getMemberProfileImg", email);
	}

	@Override
	public void updateMemberProfileImg(AttachFileVO attachFileVO) throws SQLException {
		sqlSession.update("attachFile-mapper.updateMemberProfileImg", attachFileVO);
	}
}
