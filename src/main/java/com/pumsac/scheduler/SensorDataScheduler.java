package com.pumsac.scheduler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ibm.icu.text.SimpleDateFormat;
import com.pumsac.service.SensorService;
import com.pumsac.vo.SensorInfoVO;
import com.pumsac.vo.SetSensorVO;

@Component("sensorScheuler")
public class SensorDataScheduler {

	@Autowired
	private SensorService sensorService;
	
	int LineCount = 0;

// 스케줄러에서 조건걸기 1회에 55개의 데이터가 추가됨.	
	@Scheduled(fixedRate=1000*60*5)
	public void insertSensorData() {
		System.out.println("스케줄러 작동");
		BufferedReader bufferReader = null;
		try {
			String year = new SimpleDateFormat("yyyy").format(new Date());
			String month = new SimpleDateFormat("MM").format(new Date());
			String day = new SimpleDateFormat("dd").format(new Date());
			
			String filePath = "D:\\SensorLog" + "\\" + year + "\\" + month + "\\" + day + "\\SensorData_log.csv";
			
			FileReader fileReader = new FileReader(filePath);
			
			if (fileReader == null) { return; }
			
			bufferReader = new BufferedReader(fileReader);
			
			String textLine = null;
			
			List<SensorInfoVO> tempList = new ArrayList<SensorInfoVO>();
			List<SensorInfoVO> humiList = new ArrayList<SensorInfoVO>();
			List<SensorInfoVO> distanceList = new ArrayList<SensorInfoVO>();
			List<SensorInfoVO> disIndexList = new ArrayList<SensorInfoVO>();
			
			for (int i = 0; i < LineCount; i++) {
				bufferReader.readLine();
			}
			
			while ((textLine=bufferReader.readLine()) != null) {
				String[] sensorData = textLine.split(",");
				
				int temp = Integer.parseInt(sensorData[1]);
				int humi = Integer.parseInt(sensorData[2]);
				int distance = Integer.parseInt(sensorData[3]);
				int disIndex = Integer.parseInt(sensorData[4]);
				int roomNo = Integer.parseInt(sensorData[5]);
				Date checkDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sensorData[6]);
				
				SetSensorVO setSensor = sensorService.getSettingSensorInfo(roomNo);
				int DiscomfortIndex = (int) Math.round(0.81 * temp + 0.01 * (humi *(0.99 * temp - 14.3))+ 46.3);
				
				/* 위험검사용 If문 */
				if (roomNo > 0) {
					if (temp > setSensor.getTemperatured()) { tempList.add(getSensorInfoVO(1, "화재", temp, roomNo, checkDate)); }
					if (humi > setSensor.getHumidity()) { humiList.add(getSensorInfoVO(2, "침수", humi, roomNo, checkDate)); }
					if (distance > setSensor.getDistance()) { distanceList.add(getSensorInfoVO(3, "문단속", distance, roomNo, checkDate)); }
					if (disIndex > DiscomfortIndex) { disIndexList.add(getSensorInfoVO(4, "불쾌지수", disIndex, roomNo, checkDate)); }	
				}
				
				LineCount = LineCount+1;
			}
			
			// 검사용 갯수
//			int fireCount = 0;
//			int humiCount = 0;
//			int disIndexCount = 0;
//			int distanceCount = 0;
			int fireCount = 48;
			int humiCount = 50;
			int disIndexCount = 30;
			int distanceCount = 50;
			
			/* 화재발생 */
			if (tempList != null && tempList.size() > fireCount) {
				sensorService.registDangerSensorData(tempList);
				tempList = null;
			}
			if (humiList != null && humiList.size() > humiCount) {
				sensorService.registDangerSensorData(humiList);
				humiList = null;
			}
			/* 불쾌지수 너무높음 */
			if (disIndexList != null && disIndexList.size() > disIndexCount) {
				sensorService.registDangerSensorData(disIndexList);
				disIndexList = null;
			}
			/* 문단속여부 */
			if (distanceList != null && distanceList.size() > distanceCount) {
				sensorService.registDangerSensorData(distanceList);
				distanceList = null;
			}
			
			System.out.println("LineCount : " + LineCount);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferReader != null) {
					bufferReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Scheduled(cron="* * 0 * * *")
	public void resetCount() {
		LineCount = 0;
		System.out.println("읽을 로그파일 변경");
	}

	private SensorInfoVO getSensorInfoVO(int type, String reason, int val, int roomNo, Date checkDate) {
		SensorInfoVO sensorInfoVO = new SensorInfoVO();
		sensorInfoVO.setType(type);
		sensorInfoVO.setVal(val);
		sensorInfoVO.setRoomNo(roomNo);
		sensorInfoVO.setReason(reason);
		sensorInfoVO.setReadYn("n");
		sensorInfoVO.setCheckDate(checkDate);
		sensorInfoVO.setRegDate(new Date());
		return sensorInfoVO;
	}
}