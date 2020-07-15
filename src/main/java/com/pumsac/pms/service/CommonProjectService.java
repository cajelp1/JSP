package com.pumsac.pms.service;

import com.pumsac.vo.MemberVO;
import com.pumsac.vo.ProBoardInfoVO;
import com.pumsac.vo.ProjectVO;

import java.sql.SQLException;
import java.util.List;

public interface CommonProjectService {

    public List<ProjectVO> getProjectId(MemberVO vo) throws SQLException;
    public ProBoardInfoVO getProjectBoardInfo(ProBoardInfoVO vo) throws  SQLException;
}
