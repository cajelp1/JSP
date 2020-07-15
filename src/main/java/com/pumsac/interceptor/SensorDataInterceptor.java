package com.pumsac.interceptor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ibm.icu.text.SimpleDateFormat;

public class SensorDataInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean result = true;

		Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		int temp = Integer.parseInt((String) pathVariables.get("temp"));
		int humi = Integer.parseInt((String) pathVariables.get("humi"));
		int distance = Integer.parseInt((String) pathVariables.get("distance"));
		int roomNo = Integer.parseInt((String) pathVariables.get("roomNo"));
		int DiscomfortIndex = (int) Math.round(0.81 * temp + 0.01 * (humi *(0.99 * temp - 14.3))+ 46.3);

		System.out.println("temp : " + temp);
		System.out.println("humi : " + humi);
		System.out.println("distance : " + distance);
//		System.out.println("roomNo : " + roomNo);
//		System.out.println("불쾌지수 : " + DiscomfortIndex);
		// 불쾌지수 70~80 : 일부사람 불쾌, 80~83 : 절반불쾌, 83~ : 대부분 불쾌
		try {
			WriteLogFileToCSV(temp, humi, distance, DiscomfortIndex, roomNo);
		} catch (IOException e) {
			System.out.println("센서데이터 로그파일 생성 에러발생");
		}

		return result;
	}

	private void WriteLogFileToCSV(int temp, int humi, int distance, int DiscomfortIndex, int roomNo) throws IOException {
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String year = new SimpleDateFormat("yyyy").format(new Date());
		String month = new SimpleDateFormat("MM").format(new Date());
		String day = new SimpleDateFormat("dd").format(new Date());
		
		
		String logFileStr = "[Sensor:Data]," + temp + "," + humi + "," + distance + "," + DiscomfortIndex + "," + roomNo + "," + time;
		
		String savePath = "D:\\SensorLog" + "\\" + year + "\\" + month + "\\" + day;
		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		
		String logFilePath = savePath + file.separator + "SensorData_log.csv";
		FileWriter out = new FileWriter(logFilePath, true);
		BufferedWriter writer = new BufferedWriter(out);

		writer.write(logFileStr);
		writer.newLine();
		writer.close();
	}


}
