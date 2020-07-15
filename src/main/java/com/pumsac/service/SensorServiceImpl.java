package com.pumsac.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumsac.command.PageMaker;
import com.pumsac.command.SearchCriteria;
import com.pumsac.dao.SensorDao;
import com.pumsac.vo.SensorInfoVO;
import com.pumsac.vo.SetSensorVO;

@Service("sensorServiceImpl")
public class SensorServiceImpl implements SensorService {

	@Autowired
	private SensorDao sensorDao;

	@Override
	public Map<String, Object> getDangerSensorData(Map<String, Object> map) throws SQLException {
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		List<SensorInfoVO> dangerDataList = sensorDao.selectDangerSensorDataByRoomNo(map);
		int totalCount = sensorDao.selectDangerSensorDataTotalCountByRoomNo((int)map.get("roomNo"));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri((SearchCriteria)map.get("cri"));
		pageMaker.setTotalCount(totalCount);
		
		
		dataMap.put("dangerDataList", dangerDataList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}
	
	@Override
	public SetSensorVO getSettingSensorInfo(int roomNo) throws SQLException {
		return sensorDao.selectSettingSensorInfo(roomNo);
	}

	@Override
	public void mergeSetSensor(SetSensorVO setSensorVO) throws SQLException {
		sensorDao.mergeSetSensorInfo(setSensorVO);
	}

	@Override
	public void registDangerSensorData(List<SensorInfoVO> sensorInfoList) throws SQLException {
		int val = 0;
		SensorInfoVO sensor = null;
		for (SensorInfoVO sensorInfoVO : sensorInfoList) {
			if (sensorInfoVO.getVal() > val) {
				val = sensorInfoVO.getVal();
				sensor = sensorInfoVO;
			}
		}
		sensorDao.insertDangerData(sensor);
	}

	@Override
	public int getDangeDataMaxNo() throws SQLException {
		return sensorDao.selectDangeDataMaxNo();
	}
	
	@Override
	public List<SensorInfoVO> getDangerSensorDataAtReadN(Map<String, Object> map) throws SQLException {
		List<SensorInfoVO> list = sensorDao.selectDangerSensorDataAtReadN(map);
		sensorDao.updateSensorInfoReadyn(map);
		
		return list;
	}

	
}
