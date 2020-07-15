package com.pumsac.pms.service;

import com.pumsac.pms.dao.PmsUserDashboardDao;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.PrjPsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("pmsUserDashboardService")
public class PmsUserDashboardServiceImpl implements PmsUserDashboardService{
    
	@Autowired
    PmsUserDashboardDao pmsUserDashboardDao;
    
	@Override
    public List<PrjPsVO> getloginUserJob(MemberVO  memberVO) throws SQLException {
        return pmsUserDashboardDao.selectJobByEmail(memberVO);
    }

    @Override
    public List<PrjPsVO> getProjectTotalStageOfCompletionByProjectId(String projectId) throws SQLException {
        return pmsUserDashboardDao.selectProjectTotalStageOfCompletionByProjectId(projectId);
    }

    @Override
    public List<PrjPsVO> getProjectIndividualStageOfCompletionByPrjPsVO(PrjPsVO vo) throws SQLException {
        return null;
    }
}
