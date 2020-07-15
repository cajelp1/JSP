package com.spring.scheduler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.spring.dto.MemberLoginLogVO;
import com.spring.service.LoginLogService;

//@Component("taskScheduler")
public class MemberLoginLogTaskScheduler {
	
	//@Autowired
	private LoginLogService logService;
	public void setLogService(LoginLogService logService) {
		this.logService=logService;
	}
	
	//@Scheduled(fixedRate=1000*10)
	//@Scheduled(cron="0 0 2 * * *")
	public void testScheduler() {
		
		BufferedReader in=null;
		
		try {
			String filePath="d:\\log\\login_user_log.csv";
			FileReader reader = new FileReader(filePath);
			
			in=new BufferedReader(reader);
			
			String textLine=null;
			
			List<MemberLoginLogVO> logList=new ArrayList<MemberLoginLogVO>();
			
			while((textLine=in.readLine())!=null) {
				String[] logData=textLine.split(",");
				
				MemberLoginLogVO logVO=new MemberLoginLogVO();
				logVO.setId(logData[1]);
				logVO.setPhone(logData[2]);
				logVO.setEmail(logData[3]);
				logVO.setIpAddress(logData[4]);
				
				logVO.setIndate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(logData[5]));
				
				logList.add(logVO);				
			}				
		
			logService.write(logList);		
			
			System.out.println("schedule run!");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(in!=null) in.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}






