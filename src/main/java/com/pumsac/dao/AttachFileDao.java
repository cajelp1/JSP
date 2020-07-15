package com.pumsac.dao;

import java.sql.SQLException;

import com.pumsac.vo.AttachFileVO;

public interface AttachFileDao {

	public void registMemberProfileImage(AttachFileVO vo) throws SQLException;

	public void attachFilePmsNoticBoard(AttachFileVO vo) throws SQLException;

	public int getAttachFileSeq() throws SQLException;

	public void registSiteNoticeAttach(AttachFileVO vo) throws SQLException;

	public int getBoardCurrentSeq() throws SQLException;

	public AttachFileVO getAttachFileBySno(int sno) throws SQLException;

	public int proBoardCurrentSeq() throws SQLException;

	public AttachFileVO getFileListAtPmsNoticBoardByPno(int pno) throws SQLException;

	public AttachFileVO getFileListAtPmsNoticBoardByOriginFileName(String fielName) throws SQLException;

	public void modifyAttachFile(AttachFileVO vo) throws SQLException;

	public void registSiteNoticeAttachAtModify(AttachFileVO vo);

	public AttachFileVO selectAttachFileInfo(int fileNo) throws SQLException;

	public void deleteFileInfo(int fileNo) throws SQLException;

	public AttachFileVO getMemberProfileImg(String email) throws SQLException;

	public void updateMemberProfileImg(AttachFileVO attachFileVO) throws SQLException;
}
