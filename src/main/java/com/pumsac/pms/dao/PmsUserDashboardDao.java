package com.pumsac.pms.dao;

import com.pumsac.vo.MemberVO;
import com.pumsac.vo.PrjPsVO;

import java.sql.SQLException;
import java.util.List;

public interface PmsUserDashboardDao {
    
	List<PrjPsVO>selectJobByEmail(MemberVO vo) throws SQLException;
    
	List<PrjPsVO>selectProjectTotalStageOfCompletionByProjectId(String projectId) throws SQLException;
    
    List<PrjPsVO>selectProjectIndividualStageOfCompletionByPrjPsVO(PrjPsVO vo) throws SQLException;
	
}
