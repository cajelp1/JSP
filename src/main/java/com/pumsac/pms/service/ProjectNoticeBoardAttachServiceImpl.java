package com.pumsac.pms.service;

import com.pumsac.pms.dao.ProjectNoticeBoardAttachFileDao;
import com.pumsac.vo.ProjectNoticeAttachFileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service("projectNoticeBoardAttachService")
public class ProjectNoticeBoardAttachServiceImpl implements ProjectNoticeBoardAttachService {

    @Autowired
    ProjectNoticeBoardAttachFileDao projectNoticeBoardAttachFileDao;

    @Override
    public void registProjectNoticeAttach(ProjectNoticeAttachFileVO vo) throws SQLException {
        projectNoticeBoardAttachFileDao.insertNoticeAttach(vo);
    }

    @Override
    public void modifyProjectNoticeAttachFile(ProjectNoticeAttachFileVO vo) throws SQLException {

    }

    @Override
    public void removeProjectNoticeFileInfo(int fileNo) throws SQLException {
         projectNoticeBoardAttachFileDao.deleteNoticeAttachByNo(fileNo);
    }

    @Override
    public ProjectNoticeAttachFileVO getFileListAtPmsNoticBoardByNo(int no) throws SQLException {
        return projectNoticeBoardAttachFileDao.selectNoticeAttachByNo(no);
    }

    @Override
    public ProjectNoticeAttachFileVO getFileListAtPmsNoticBoardByOriginFileName(String fileName) throws SQLException {
        return projectNoticeBoardAttachFileDao.selectNoticeAttachByOriginFileName(fileName);
    }
}
