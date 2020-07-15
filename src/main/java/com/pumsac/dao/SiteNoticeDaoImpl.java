package com.pumsac.dao;

import com.pumsac.command.SearchCriteria;
import com.pumsac.vo.BoardVO;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository("SiteNoticeDao")
public class SiteNoticeDaoImpl implements SiteNoticeDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertSiteNotice(BoardVO board) throws SQLException {

		sqlSession.update("site_notice-mapper.insertSiteNotice", board);

	}

	@Override
	public List<BoardVO> selectAllSiteNotice(SearchCriteria cri) throws SQLException {
		
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<BoardVO> list = sqlSession.selectList("site_notice-mapper.selectAllSiteNotice",cri,rowBounds);
		return list;
	}

	@Override
	public void updateSiteNotice(BoardVO board) throws SQLException {

		sqlSession.update("site_notice-mapper.updateSiteNotice", board);

	}

	@Override
	public void deleteSiteNotice(int sno) throws SQLException {

		sqlSession.update("site_notice-mapper.deleteSiteNotice", sno);

	}

	@Override
	public void increaseSiteNoticeViewCount(int sno) throws SQLException {
		sqlSession.update("site_notice-mapper.increaseSiteNoticeViewCount", sno);
	}

	@Override
	public int getNoticeSequence() throws SQLException {
		int no = 0;
		no = sqlSession.selectOne("site_notice-mapper.getNoticeSequence");
		return no;
	}

	@Override
	public BoardVO selectDetailSiteNotice(int sno) throws SQLException {
		BoardVO board = new BoardVO();
		board = sqlSession.selectOne("site_notice-mapper.selectDetailSiteNotice", sno);
		return board;
	}

	@Override
	public int selectCountSiteNotice(SearchCriteria cri) throws SQLException {
		int cnt = 0;
		cnt = sqlSession.selectOne("site_notice-mapper.selectCountSiteNotice", cri);
		return cnt;
	}

	@Override
	public List<BoardVO> selectCurrentSiteNotice(SearchCriteria cri) throws SQLException {
		List<BoardVO> list = sqlSession.selectList("site_notice-mapper.selectCurrentSiteNotice",cri);
		return list;
	}

}
