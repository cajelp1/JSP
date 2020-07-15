package com.pumsac.service;

import java.sql.SQLException;

import com.pumsac.vo.AttachFileVO;

public interface AttachFileService {

	public void registMemberProfileImage(AttachFileVO vo) throws SQLException;

	public void registSiteNoticeAttach(AttachFileVO vo) throws SQLException;
	
	public AttachFileVO getAttachFileBySno(int sno) throws SQLException;

	public void modifyAttachFile(AttachFileVO vo) throws SQLException;

	public void registSiteNoticeAttachAtModify(AttachFileVO attachVO) throws SQLException;

	public AttachFileVO getFileInfo(int fileNo) throws SQLException;

	public void removeFileInfo(int fileNo) throws SQLException;

	public AttachFileVO getProfileImage(String email) throws SQLException;

	public void modifyProfileFile(AttachFileVO attachFileVO) throws SQLException;
	
}
