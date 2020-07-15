package com.pumsac.pms.dao;

import com.pumsac.vo.MemberVO;
import com.pumsac.vo.ProBoardInfoVO;
import com.pumsac.vo.ProjectVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
@Repository("commonProjectDao")
public class CommonProjectDaoImpl implements CommonProjectDao {
    @Autowired
    private SqlSession sqlSession;
    @Override
    public List<ProjectVO> selectProjectIdById(MemberVO vo) throws SQLException {
        List<ProjectVO> list=sqlSession.selectList("pms-mypage-mapper.selectProjectIdById",vo);
        return list;
    }
    @Override
    public ProBoardInfoVO selectNoticeByProjectId(ProBoardInfoVO vo) throws SQLException {
        return sqlSession.selectOne("projectCommon-mapper.selectByProjectId",vo);
    }

}
