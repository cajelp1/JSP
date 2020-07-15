package com.pumsac.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.pumsac.vo.SensorInfoVO;
import com.pumsac.vo.SetSensorVO;

public interface SensorService {

	Map<String, Object> getDangerSensorData(Map<String, Object> map) throws SQLException;

	SetSensorVO getSettingSensorInfo(int roomNo) throws SQLException;

	void mergeSetSensor(SetSensorVO setSensorVO) throws SQLException;

	void registDangerSensorData(List<SensorInfoVO> sensorInfoList) throws SQLException;

	int getDangeDataMaxNo() throws SQLException;

	List<SensorInfoVO> getDangerSensorDataAtReadN(Map<String, Object> map) throws SQLException;
	
	
}