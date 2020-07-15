package com.pumsac.controller;

import com.pumsac.command.SearchCriteria;
import com.pumsac.service.MemberService;
import com.pumsac.service.SensorService;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.SensorInfoVO;
import com.pumsac.vo.SetSensorVO;

import net.nurigo.java_sdk.api.Message;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SensorController {
	
	@Autowired
	private SensorService sensorService;
	
	@Autowired
	private MemberService MemberService;
	
	/**
	 * @throws Exception 
	 * @기능 : 관제시스템에서 이상 발생시 알림을 보내기 위한 컨트롤러
	 */
	@RequestMapping(value="/reciever/temp/{temp}/humi/{humi}/distance/{distance}/roomNo/{roomNo}")
	public void alarm(@PathVariable("temp") int temp
			,@PathVariable("humi") int humi
			,@PathVariable("distance") int distance
			,@PathVariable("roomNo") int roomNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	}
	 
	@RequestMapping(value="/site/admin/setSensor")
	public String setSensor(Model model, int roomNo) throws Exception {
		String url = "sensor";
		SetSensorVO sensorVo = sensorService.getSettingSensorInfo(roomNo);
		model.addAttribute("sensor", sensorVo);
		
		return url;
	}

	@RequestMapping(value="/dsp/sensor/setSensor", method=RequestMethod.GET)
	@ResponseBody
	ResponseEntity<String> mergeSetSensor(SetSensorVO setSensorVO) throws Exception{
		ResponseEntity<String> entity = null;
		try {
			sensorService.mergeSetSensor(setSensorVO);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (SQLException e) {
			entity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	@RequestMapping(value="/dsp/sensor/getDangerSensorData", method=RequestMethod.GET)
	public String getDangerSensorDataGet(Model model, int roomNo, SearchCriteria cri) throws SQLException{
		String url = "site/admin/location/dangerSensor";
		
		Map<String, Object> map = new HashMap<String, Object>();
		cri.setPerPageNum(5);
		map.put("cri", cri);
		map.put("roomNo", roomNo);
		
		Map<String, Object> dangerData = sensorService.getDangerSensorData(map);
		SetSensorVO settingData = sensorService.getSettingSensorInfo(roomNo);
		
		model.addAllAttributes(dangerData);
		model.addAttribute("settingData", settingData);
		model.addAttribute("roomNo", roomNo);
		return url;
	}
	
	@RequestMapping(value="/dsp/sensor/getSettingSensorData", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<SetSensorVO> getSettingSensorData(int roomNo) throws Exception{
		ResponseEntity<SetSensorVO> entity = null;
		SetSensorVO sensorData = null;
		try {
			sensorData = sensorService.getSettingSensorInfo(roomNo);
			entity = new ResponseEntity<SetSensorVO>(sensorData, HttpStatus.OK);
		} catch (SQLException e) {
			entity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	@RequestMapping(value="/dsp/sensor/dangeDataMaxNo", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Integer> dangerDataCount() throws Exception{
		ResponseEntity<Integer> entity = null;
		try {
			int cnt = sensorService.getDangeDataMaxNo();
			entity = new ResponseEntity<Integer>(cnt, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	@RequestMapping(value="/dsp/sensor/dangerAlarm", method=RequestMethod.GET)
	public String dangerAlarm(Model model, int maxNo, int currentNo) throws Exception{
		String url = "site/admin/dangerAlarm";
		
		Map<String, Object> map = new HashMap<>();
		map.put("maxNo", maxNo);
		map.put("currentNo", currentNo);
		
		List<SensorInfoVO> dangerList = sensorService.getDangerSensorDataAtReadN(map);
		model.addAttribute("dangerList", dangerList);
		
		List<MemberVO> adminList = MemberService.getAdminList();
		
		for (MemberVO admin : adminList) {
			for (SensorInfoVO sensor : dangerList) {
				if (!sensor.getReason().equals("문단속") || !sensor.getReason().equals("불쾌지수")) {
					String api_key = "NCSIZYZBIEEPCQCY";
			        String api_secret = "JWFOMUWT17LSKNVXLYP8LVZNLXOOYRQH";
			        Message coolsms = new Message(api_key, api_secret);
			        HashMap<String, String> params = new HashMap<String, String>();
			        params.put("to", admin.getPhoneNumber());
			        params.put("from", "010-2125-4151"); //무조건 자기번호 (인증)
			        params.put("type", "SMS");
			        params.put("text", sensor.getRoomName() + "방에서 " + sensor.getReason() + "발생!!");
			        params.put("app_version", "test app 1.2");
			        JSONObject smsResult = coolsms.send(params); // 보내기
				}
			}
		}
		
		return url;
	}
/*	
	@RequestMapping(value="/dsp/sensor/dangerAlarm", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<SensorInfoVO>> dangerAlarm(int count) throws Exception{
		ResponseEntity<List<SensorInfoVO>> entity = null;
		try {
			List<SensorInfoVO> list = sensorService.getDangerSensorDataAtReadN(count);
			entity = new ResponseEntity<List<SensorInfoVO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<SensorInfoVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}

	@RequestMapping(value="/dsp/sensor/getDangerSensorData", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<SensorInfoVO>> getSensorData(int roomNo) throws Exception{
		ResponseEntity<List<SensorInfoVO>> entity = null;
		List<SensorInfoVO> sensorData = null;
		try {
			sensorData = sensorService.getDangerSensorData(roomNo);
			entity = new ResponseEntity<List<SensorInfoVO>>(sensorData, HttpStatus.OK);
		} catch (SQLException e) {
			entity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
*/	
}
