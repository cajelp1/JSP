package com.pumsac.pms.dao;

import com.pumsac.vo.MemberVO;
import com.pumsac.vo.PrjPsVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
@Repository("pmsUserDashboardDao")
public class PmsUserDashboardDaoImpl implements PmsUserDashboardDao{
    @Autowired
    private SqlSession sqlSession;
    @Override
    public List<PrjPsVO> selectJobByEmail(MemberVO vo) throws SQLException {
        return sqlSession.selectList("pms-userDashboard-mapper.selectJobByEmail",vo);
    }

    @Override
    public List<PrjPsVO> selectProjectTotalStageOfCompletionByProjectId(String projectId) throws SQLException {
        return  sqlSession.selectList("pms-userDashboard-mapper.selectProjectTotalStageOfCompletionByProjectId",projectId);
    }

    @Override
    public List<PrjPsVO> selectProjectIndividualStageOfCompletionByPrjPsVO(PrjPsVO vo) throws SQLException {
        return sqlSession.selectList("pms-userDashboard-mapper.selectProjectIndividualStageOfCompletionByPrjPsVO",vo);
    }
	
}
