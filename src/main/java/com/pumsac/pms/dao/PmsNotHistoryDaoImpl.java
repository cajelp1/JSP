package com.pumsac.pms.dao;

import com.pumsac.command.SearchCriteria;
import com.pumsac.vo.NotHistoryVO;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository("notHistoryDao")
public class PmsNotHistoryDaoImpl implements PmsNotHistoryDao{

	@Autowired
	private SqlSession sqlsession;
	
	@Override
	public int selectNotHistoryTotalCount(Map<String, Object> map) throws SQLException {
		return sqlsession.selectOne("notHistory-mapper.selectNotHistoryTotalCount", map);
	}
	@Override
	public List<NotHistoryVO> selectNotHistoryList(Map<String, Object> map) throws SQLException {
		SearchCriteria cri = (SearchCriteria) map.get("cri");
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<NotHistoryVO> list = null;
		
		list = sqlsession.selectList("notHistory-mapper.selectNotHistoryList", map, rowBounds);
		
		return list;
	}

	@Override
	public int selectNotHistoryTotalCountReadN(Map<String, Object> map) throws SQLException {
		return sqlsession.selectOne("notHistory-mapper.selectNotHistoryTotalCountReadN", map);
	}
	@Override
	public List<NotHistoryVO> selectNotHistoryListReadN(Map<String, Object> map) throws SQLException {
		List<NotHistoryVO> list = null;
		
		list = sqlsession.selectList("notHistory-mapper.selectNotHistoryListReadN", map);
		
		return list;
	}
	
	@Override
	public void updateReadYN(int no) throws SQLException {
		sqlsession.update("notHistory-mapper.updateReadYN", no);
	}

	@Override
	public void InsertNotHistory(NotHistoryVO historyVO) throws SQLException {
		sqlsession.update("notHistory-mapper.InsertNotHistory", historyVO);
		
	}
	@Override
	public int selectSeq() throws SQLException {
		return sqlsession.selectOne("notHistory-mapper.selectSeq");
	}
	@Override
	public NotHistoryVO getNotHistoryDetail(int no) throws SQLException {
		return sqlsession.selectOne("notHistory-mapper.getNotHistoryDetail", no);
	}
}
