package com.pumsac.pms.service;

import com.pumsac.vo.MemberVO;
import com.pumsac.vo.PrjPsVO;

import java.sql.SQLException;
import java.util.List;

public interface PmsUserDashboardService {
    public List<PrjPsVO> getloginUserJob(MemberVO vo) throws SQLException;
    List<PrjPsVO>getProjectTotalStageOfCompletionByProjectId(String projectId) throws SQLException;
    List<PrjPsVO>getProjectIndividualStageOfCompletionByPrjPsVO(PrjPsVO vo) throws SQLException;
}
