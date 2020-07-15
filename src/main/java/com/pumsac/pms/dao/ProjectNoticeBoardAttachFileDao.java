package com.pumsac.pms.dao;

import com.pumsac.vo.ProjectNoticeAttachFileVO;

import java.sql.SQLException;

public interface ProjectNoticeBoardAttachFileDao{
    void insertNoticeAttach(ProjectNoticeAttachFileVO projectNoticeAttachFileVO) throws SQLException;
    ProjectNoticeAttachFileVO selectNoticeAttachByNo(int no) throws SQLException;
    ProjectNoticeAttachFileVO selectNoticeAttachByOriginFileName(String orginFileName) throws  SQLException;
    void deleteNoticeAttachByNo(int no) throws SQLException;

}
