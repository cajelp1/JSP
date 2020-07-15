package com.pumsac.pms.dao;

import com.pumsac.vo.MemberVO;
import com.pumsac.vo.ProBoardInfoVO;
import com.pumsac.vo.ProjectVO;

import java.sql.SQLException;
import java.util.List;

public interface CommonProjectDao {
   List< ProjectVO> selectProjectIdById(MemberVO vo) throws SQLException;
   ProBoardInfoVO selectNoticeByProjectId(ProBoardInfoVO vo) throws SQLException;
}
