package com.pumsac.pms.dao;

import com.pumsac.command.SearchCriteria;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.ProTeamVO;
import com.pumsac.vo.ProjectNoticeBoardVO;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("pmsNoticeBoardDao")
public class PmsNoticeBoardDaoImpl implements PmsNoticeBoardDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ProjectNoticeBoardVO> selectSearchNoticeList(SearchCriteria cri) throws SQLException {
		List<ProjectNoticeBoardVO> list = new ArrayList<ProjectNoticeBoardVO>();
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		list = sqlSession.selectList("pms-team-noticboard-mapper.selectSearchNoticeList", cri, rowBounds);
		return list;
	}

	@Override
	public int selectSearchNoticeListCount(SearchCriteria cri) throws SQLException {
		int count = sqlSession.selectOne("pms-team-noticboard-mapper.selectSearchNoticeListCount", cri);
		return count;
	}

	@Override
	public ProjectNoticeBoardVO selectByNo(int no) throws SQLException {
		ProjectNoticeBoardVO projectNoticeBoardVO = sqlSession.selectOne("pms-team-noticboard-mapper.selectByNo", no);
		return projectNoticeBoardVO;
	}

	@Override
	public int selectprojectNoiceBoardNextVal() throws SQLException {
		return sqlSession.selectOne("pms-team-noticboard-mapper.selectprojectNoiceBoardNextVal");
	}

	@Override
	public void insertNoticeBoard(ProjectNoticeBoardVO vo) throws SQLException {
		vo.setNo(selectprojectNoiceBoardNextVal());
		sqlSession.update("pms-team-noticboard-mapper.insertNoticeBoard", vo);
	}

	@Override
	public int selectProjectNoticeBoardCurrentval() throws SQLException {
		return sqlSession.selectOne("pms-team-noticboard-mapper.selectProjectNoticeBoardCurrentval");
	}

	@Override
	public void viewCountIncrease(int no) throws SQLException {
		sqlSession.update("pms-team-noticboard-mapper.viewCountIncrease",no);
	}

	@Override
	public int selectJobByProjectId(ProTeamVO vo) throws SQLException {
		return sqlSession.selectOne("pms-team-noticboard-mapper.selectJobByProjectId",vo);
	}

	@Override
	public void deleteByNo(int no) throws SQLException {
		sqlSession.update("pms-team-noticboard-mapper.deleteByNo",no);
	}

	@Override
	public List<MemberVO> selectEmailByProjectId(int no) throws SQLException {
		return sqlSession.selectList("pms-team-noticboard-mapper.selectEmailByProjectId",no);
	}
}
