package com.pumsac.pms.dao;

import com.pumsac.vo.ProjectNoticeAttachFileVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository("projectNoticeBoardAttachDao")
public class ProjectNoticeBoardAttachFileDaoImpl  implements  ProjectNoticeBoardAttachFileDao{
    @Autowired
    private SqlSession sqlSession;
    @Override
    public void insertNoticeAttach(ProjectNoticeAttachFileVO projectNoticeAttachFileVO) throws SQLException {
        sqlSession.update("pms-team-noticboard-mapper.insertNoticeAttach",projectNoticeAttachFileVO);
    }

    @Override
    public ProjectNoticeAttachFileVO selectNoticeAttachByNo(int no) throws SQLException {
        return sqlSession.selectOne("pms-team-noticboard-mapper.selectNoticeAttachByNo",no);
    }

    @Override
    public ProjectNoticeAttachFileVO selectNoticeAttachByOriginFileName(String orginFileName) throws SQLException {
        return sqlSession.selectOne("pms-team-noticboard-mapper.selectNoticeAttachByOriginFileName",orginFileName);
    }

    @Override
    public void deleteNoticeAttachByNo(int no) throws SQLException {
        sqlSession.update("pms-team-noticboard-mapper.deleteNoticeAttachByNo",no);
    }
}
