package com.pumsac.service;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumsac.dao.AttachFileDao;
import com.pumsac.vo.AttachFileVO;

@Service("attachFileService")
public class AttachFileServiceImpl implements AttachFileService {

	@Autowired
	private AttachFileDao attachFileDao;
	
	@Resource(name="memberProfileImage")
	private String profilePath;
	
	@Override
	public void registMemberProfileImage(AttachFileVO vo) throws SQLException {
		int no = attachFileDao.getAttachFileSeq();
		vo.setNo(no);
		vo.setAttachDirectory(profilePath);
		
		attachFileDao.registMemberProfileImage(vo);
	}

	@Override
	public void registSiteNoticeAttach(AttachFileVO vo) throws SQLException {
		int no = attachFileDao.getAttachFileSeq();
//		int sno = attachFileDao.getBoardCurrentSeq();
		vo.setNo(no);
//		vo.setSno(sno);
		attachFileDao.registSiteNoticeAttach(vo);
	}
	
	@Override
	public AttachFileVO getAttachFileBySno(int sno) throws SQLException {
		return attachFileDao.getAttachFileBySno(sno);
	}

	@Override
	public void modifyAttachFile(AttachFileVO vo) throws SQLException {
		attachFileDao.modifyAttachFile(vo);
	}

	@Override
	public void registSiteNoticeAttachAtModify(AttachFileVO attachVO) throws SQLException {
		int no = attachFileDao.getAttachFileSeq();
		attachVO.setNo(no);
		attachFileDao.registSiteNoticeAttachAtModify(attachVO);
	}

	@Override
	public AttachFileVO getFileInfo(int fileNo) throws SQLException {
		return attachFileDao.selectAttachFileInfo(fileNo);
	}

	@Override
	public void removeFileInfo(int fileNo) throws SQLException {
		attachFileDao.deleteFileInfo(fileNo);
	}

	@Override
	public AttachFileVO getProfileImage(String email) throws SQLException {
		return attachFileDao.getMemberProfileImg(email);
	}

	@Override
	public void modifyProfileFile(AttachFileVO attachFileVO) throws SQLException {
		attachFileDao.updateMemberProfileImg(attachFileVO);
	}

}
