package com.pumsac.dao;

import com.pumsac.command.SearchCriteria;
import com.pumsac.vo.SensorInfoVO;
import com.pumsac.vo.SetSensorVO;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository("sensorDao")
public class SensorDaoImpl implements SensorDao{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertDangerData(SensorInfoVO sensorInfoVO) throws SQLException {
		sqlSession.update("sensor-mapper.insertDangerData", sensorInfoVO);
	}

	@Override
	public void mergeSetSensorInfo(SetSensorVO sensorVO) throws SQLException {
		sqlSession.update("sensor-mapper.mergeSetSensorInfo", sensorVO);
	}

	@Override
	public SetSensorVO selectDangerSensorDataByNo(int no) throws SQLException {
		return sqlSession.selectOne("sensor-mapper.selectDangerSensorDataByNo", no);
	}

	@Override
	public int selectDangerSensorDataTotalCountByRoomNo(int roomNo) throws SQLException {
		return sqlSession.selectOne("sensor-mapper.selectDangerSensorDataTotalCountByRoomNo", roomNo);
	}
	
	@Override
	public List<SensorInfoVO> selectDangerSensorDataByRoomNo(Map<String, Object> map) throws SQLException {
		SearchCriteria cri = (SearchCriteria) map.get("cri");
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<SensorInfoVO> list = sqlSession.selectList("sensor-mapper.selectDangerSensorDataByRoomNo",map,rowBounds);
		return list;
	}

	@Override
	public SetSensorVO selectSettingSensorInfo(int roomNo) throws SQLException {
		return sqlSession.selectOne("sensor-mapper.selectSettingSensorInfo", roomNo);
	}

	@Override
	public int selectDangeDataMaxNo() throws SQLException {
		return sqlSession.selectOne("sensor-mapper.selectDangeDataMaxNo");
	}
	
	@Override
	public List<SensorInfoVO> selectDangerSensorDataAtReadN(Map<String, Object> map) throws SQLException {
		return sqlSession.selectList("sensor-mapper.selectDangerSensorDataAtReadN", map);
	}

	@Override
	public void updateSensorInfoReadyn(Map<String, Object> map) throws SQLException {
		sqlSession.update("sensor-mapper.updateSensorInfoReadyn", map);
	}

}
