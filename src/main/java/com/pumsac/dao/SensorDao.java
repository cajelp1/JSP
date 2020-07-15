package com.pumsac.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.pumsac.vo.SensorInfoVO;
import com.pumsac.vo.SetSensorVO;

public interface SensorDao {
	void insertDangerData(SensorInfoVO sensorInfoVO) throws SQLException;
	
	SetSensorVO selectDangerSensorDataByNo(int no) throws SQLException;
	
	List<SensorInfoVO> selectDangerSensorDataByRoomNo(Map<String, Object> map) throws SQLException;
	
	SetSensorVO selectSettingSensorInfo(int roomNo) throws SQLException;

	void mergeSetSensorInfo(SetSensorVO setSensorVO) throws SQLException;

	int selectDangerSensorDataTotalCountByRoomNo(int roomNo) throws SQLException;

	int selectDangeDataMaxNo() throws SQLException;
	
	List<SensorInfoVO> selectDangerSensorDataAtReadN(Map<String, Object> map) throws SQLException;
	
	void updateSensorInfoReadyn(Map<String, Object> map) throws SQLException;
}
