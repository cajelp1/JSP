package com.pumsac.pms.dao;

import com.pumsac.command.SearchCriteria;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.ProTeamVO;
import com.pumsac.vo.ProjectNoticeBoardVO;

import java.sql.SQLException;
import java.util.List;

public interface PmsNoticeBoardDao {
	List<ProjectNoticeBoardVO>selectSearchNoticeList(SearchCriteria cri)throws SQLException;
    int selectSearchNoticeListCount(SearchCriteria cri) throws SQLException;
    ProjectNoticeBoardVO selectByNo(int no) throws SQLException;
    int selectprojectNoiceBoardNextVal() throws  SQLException;

    void insertNoticeBoard(ProjectNoticeBoardVO vo)throws SQLException;
   int  selectProjectNoticeBoardCurrentval() throws SQLException;
   void  viewCountIncrease(int no)throws SQLException;
   int selectJobByProjectId(ProTeamVO vo) throws SQLException;
   void deleteByNo(int no) throws  SQLException;
   List<MemberVO> selectEmailByProjectId(int no) throws SQLException;
}
