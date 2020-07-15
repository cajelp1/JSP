package com.pumsac.pms.service;

import com.pumsac.vo.ProjectNoticeAttachFileVO;

import java.sql.SQLException;

public interface ProjectNoticeBoardAttachService {
    public void registProjectNoticeAttach(ProjectNoticeAttachFileVO vo) throws SQLException;
    public void modifyProjectNoticeAttachFile(ProjectNoticeAttachFileVO vo) throws SQLException;
    public void removeProjectNoticeFileInfo(int fileNo) throws SQLException;
    public ProjectNoticeAttachFileVO getFileListAtPmsNoticBoardByNo(int pno) throws SQLException;

    public ProjectNoticeAttachFileVO getFileListAtPmsNoticBoardByOriginFileName(String fileRName) throws SQLException;
}
