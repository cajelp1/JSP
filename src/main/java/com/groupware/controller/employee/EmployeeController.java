package com.groupware.controller.employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.groupware.dto.CareerVO;
import com.groupware.dto.EmployeeVO;
import com.groupware.exception.IdNotFoundException;
import com.groupware.exception.InvalidPasswordException;
import com.groupware.request.EmployeeRegistRequest;
import com.groupware.request.ModifyEmployeeRequest;
import com.groupware.request.RegistEmployeeRequest;
import com.groupware.request.SearchCriteria;
import com.groupware.security.CustomAuthentication;
import com.groupware.security.User;
import com.groupware.service.employee.DepartmentService;
import com.groupware.service.employee.EmployeeService;
import com.groupware.service.employee.PositionService;
import com.groupware.utils.FileUpload;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DepartmentService deptService;
	@Autowired
	private PositionService positionService;
	
	@Resource(name="employeeAttachPath")
	private String employeeAttachpath;
	
	@RequestMapping("list")	//@GetMapping("list") : spring 4.3
	public ModelAndView list(SearchCriteria cri, ModelAndView mnv) throws Exception{
		String url = "employee/employee_list";
		
		mnv.addAllObjects(employeeService.getEmployeeList(cri));
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value="/regist",method=RequestMethod.GET)
	public String registGet(Model model) throws Exception{
		String url = "employee/regist";
		
		model.addAttribute("positionList",positionService.getPosotionList());
		model.addAttribute("deptList", deptService.getDeptList());
		
		return url;
	}
	
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String detail(String id, Model model) throws Exception{
		String url = "employee/detail";
		
		Map<String, Object> dataMap = employeeService.getEmployee(id);
		
		EmployeeVO employee = (EmployeeVO) dataMap.get("employee");
		EmployeeVO register = 
				(EmployeeVO) employeeService.getEmployee(employee.getRegister()).get("employee");
		
		dataMap.put("register", register);
		model.addAllAttributes(dataMap);
		
		return url;
	}
	
	//회원가입 진행
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public void registPost(RegistEmployeeRequest empReq, Model model) throws Exception{
		
		String url = "employee/regist_ok";
		
		EmployeeVO employee = empReq.toEmployeeVO();
		List<CareerVO> careers = new ArrayList<CareerVO>();
		
		if(careers!=null) {
			for(CareerVO career : empReq.getCareers()) {
				career.setId(employee.getId());
				careers.add(career);
			}
		}
		
		//첨부파일 저장 : picture, licenseDoc, graduDoc, scoreDoc
		MultipartFile[] files = { empReq.getPicture(), empReq.getLicenseDoc(),
									empReq.getGraduDoc(), empReq.getScoreDoc() };
		List<String> saveFileName = new ArrayList<String>();
		for(MultipartFile file : files) {
			if(file==null) continue;
			String fileName = UUID.randomUUID().toString().replace("-", "") + "$$"
							+ file.getOriginalFilename();
			File savePath = new File(employeeAttachpath + File.separator + employee.getId());
			if(!savePath.exists()) {
				savePath.mkdirs();
			}
			
			file.transferTo(new File(savePath, fileName));
			saveFileName.add(fileName);
		}
		
		//employeeVO에 각 attach set.
		employee.setPicture(saveFileName.get(0));
		employee.setLicenseDoc(saveFileName.get(1));
		employee.setGraduDoc(saveFileName.get(2));
		employee.setScoreDoc(saveFileName.get(3));
		
		System.out.println(employee);
		System.out.println(careers);
		
		employeeService.regist(employee, careers);
		
		model.addAttribute("employee", employee);
		
		/*
		EmployeeVO employee = request.getEmployeeVO();
		List<CareerVO> careerList = request.getCareerVO();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//파일 저장
		try {
			if(request.getPicture().getOriginalFilename() != null) {
				String picName = FileUpload.saveFile(request.getPicture(), picturePath);
				employee.setPicture(picName);
			}
			if(request.getLicenseDoc().getOriginalFilename() != null) {
				String licenseName = FileUpload.saveFile(request.getLicenseDoc(), employeeAttachpath);
				employee.setLicenseDoc(licenseName);
			}
			if(request.getGraduDoc().getOriginalFilename() != null) {
				String gradName = FileUpload.saveFile(request.getGraduDoc(), employeeAttachpath);
				employee.setGraduDoc(gradName);
			}
			if(request.getScoreDoc().getOriginalFilename() != null) {
				String scoreName = FileUpload.saveFile(request.getScoreDoc(), employeeAttachpath);
				employee.setScoreDoc(scoreName);
			}
		} catch(Exception e) {
			out.println("<script>");
			out.println("alert('파일 저장 중 오류가 발생했습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
		}
		
		//DB저장
		try {
			employeeService.regist(employee, careerList);
			
			out.println("<script>");
			out.println("alert('회원가입 완료');");
			out.println("window.close();");
			out.println("</script>");
			
		}catch(Exception e) {
			out.println("<script>");
			out.println("alert('회원 가입에 실패했습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
		}
		*/
		return;
	}
	
	
	//로그인 체크
	@RequestMapping("/CheckId")
	@ResponseBody
	public ResponseEntity<Map<String,Boolean>> checkId(String id,
											HttpServletResponse response) throws Exception{
		ResponseEntity<Map<String,Boolean>> entity = null;
		Map<String,Boolean> result = new HashMap<String, Boolean>();
		
		try {
			employeeService.login(id, "");
			result.put("result", false);
		}catch(IdNotFoundException e) {	//아이디가 없음
			result.put("result", true);
		}catch(InvalidPasswordException e) { //아이디가 존재
			result.put("result", false);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String,Boolean>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		entity = new ResponseEntity<Map<String,Boolean>>(result, HttpStatus.OK);
		return entity;
	}
	@RequestMapping("/idCheck")
	@ResponseBody
	public ResponseEntity<String> idCheck(String id) throws Exception{
		ResponseEntity<String> entity = 
				new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			employeeService.login(id,"");
		} catch(IdNotFoundException e) {
			//해당 아이디를 찾지 못해서 나는 에러. 즉 아이디가 존재하지 않음. 고로 아이디 사용 가능
			entity = new ResponseEntity<String>(HttpStatus.OK);			
		}
		return entity;
	}
	
	
	//deptno 번호 가져오기
	@RequestMapping("/deptEmpCount")
	@ResponseBody
	public ResponseEntity<Integer> deptEmpCount(@RequestParam("dept_no") String deptNum) throws Exception{
		ResponseEntity<Integer> entity = null;
		int count = employeeService.getdeptEmpCount(deptNum);
		
		entity = new ResponseEntity<Integer>(count,HttpStatus.OK);
		
		return entity;
	}
	@RequestMapping("/getEno")
	@ResponseBody
	public ResponseEntity<String> getEno(String deptno) throws Exception{
		ResponseEntity<String> entity = null;
		try {
			String eno = String.valueOf(employeeService.getdeptEmpCount(deptno)+1);
			
			entity = new ResponseEntity<String>(eno, HttpStatus.OK);
		}catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public String modifyGET(String id,Model model)throws Exception{
		String url="employee/modify";
		
		Map<String, Object> dataMap = employeeService.getEmployee(id);
		
		EmployeeVO employee = (EmployeeVO) dataMap.get("employee");
		EmployeeVO register 
		= (EmployeeVO) employeeService.getEmployee(employee.getRegister()).get("employee");
		
		dataMap.put("register", register);
		
		model.addAttribute("positionList", positionService.getPosotionList());
		model.addAttribute("deptList", deptService.getDeptList());
		
		model.addAllAttributes(dataMap);
		
		return url;
	}
	
	
	@Autowired
	private CustomAuthentication custAuth;
	
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modifyPOST(ModifyEmployeeRequest modifyReq, 
							 HttpSession session, 
							 Authentication auth,
							 Model model) throws Exception{
		String url="employee/modify_ok";
		
		EmployeeVO employee = modifyReq.toEmployeeVO();
		List<CareerVO> careers = modifyReq.getCareerList();
		
		//첨부파일 저장 : picture, licenseDoc, graduDoc, scoreDoc
		employee.setPicture(saveFile(modifyReq.getPicture(),modifyReq.getOld_picture(),modifyReq.getId()));
		employee.setLicenseDoc(saveFile(modifyReq.getLicenseDoc(),modifyReq.getOld_licenseDoc(),modifyReq.getId()));
		employee.setGraduDoc(saveFile(modifyReq.getGraduDoc(),modifyReq.getOld_graduDoc(),modifyReq.getId()));
		employee.setScoreDoc(saveFile(modifyReq.getScoreDoc(),modifyReq.getOld_scoreDoc(),modifyReq.getId()));
		
		employeeService.modify(employee, careers);
		EmployeeVO loginUser = (EmployeeVO)session.getAttribute("loginUser");
		
		/*if(loginUser!=null && loginUser.getId().equals(employee.getId())) {
			loginUser = (EmployeeVO)employeeService.getEmployee(employee.getId()).get("employee");
			session.setAttribute("loginUser", loginUser);
		}*/
		
		// 로그인한 사용자의 권한을 동적으로 업트할 경우 (변경된 경우) 로그아웃하고 로그인 할 필요 없이
		// spring security context holder의 authentication객체(보안토큰)을 재설정하면 됩니다
		
		EmployeeVO updateEmployee = null;
		if(auth.getName().equals(employee.getId())) {
			//변경된 로그인 사용자 정보를 가져온다
			updateEmployee = (EmployeeVO)employeeService.getEmployee(auth.getName()).get("employee");
			
			//권한을 갱신한다
			List<GrantedAuthority> updatedAuthorities = new ArrayList<GrantedAuthority>();
			updatedAuthorities.add(new SimpleGrantedAuthority(updateEmployee.getAuthority()));
			
			//새로운 authentication을 생성
			UsernamePasswordAuthenticationToken newAuth = 
				new UsernamePasswordAuthenticationToken(auth.getPrincipal(), 
						auth.getCredentials(), updatedAuthorities);
			
			//securityContextHolder로 새로 생성한 authentication을 setting
			SecurityContextHolder.getContext().setAuthentication(newAuth);
			
			//security의 userDetial을 갱신하기 위한 user 생성
			User user = new User(updateEmployee);
			
			//authentication detail과 session attribute를 교체한다
			newAuth.setDetails(user);
			session.setAttribute("loginUser", updateEmployee);
		}
		
		model.addAttribute("employee", employee);
		
		return url;
	}
	
	//화면에서 그림 보여주기...
	@RequestMapping("/receiveDoc")
	@ResponseBody
	public ResponseEntity<byte[]> recieveDoc(String fileName, String id) 
															throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;

		HttpHeaders headers = new HttpHeaders();

		String savePath = employeeAttachpath + File.separator + id;
		try {
			in = new FileInputStream(savePath + File.separator + fileName);

			fileName = fileName.substring(fileName.indexOf("$$") + 2);

			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition",
					"attachment;filename=\"" 
				    + new String(fileName.getBytes("utf-8"), "ISO-8859-1") + "\"");
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers,
																HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			in.close();
		}
		return entity;
	}
	
	@RequestMapping("/picture/{id}")
	public ResponseEntity<byte[]> sendPicture(@PathVariable("id") String id) throws Exception{
		ResponseEntity<byte[]> entity = null;
		
		EmployeeVO emp = (EmployeeVO) employeeService.getEmployee(id).get("employee");
		
		String fileName = emp.getPicture();
		String savePath = employeeAttachpath + File.separator + emp.getId();
		
		FileInputStream in = null;
		
		try {
			in = new FileInputStream(savePath + File.separator + fileName);
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), HttpStatus.OK);
		}catch(IOException e) {
			entity = new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}finally {
			if(in!=null)in.close();
		}
		
		return entity;
	}
	
	@RequestMapping(value="/download/list/{type}",method=RequestMethod.GET)
	public String downloadList(@ModelAttribute("cri")SearchCriteria cri, @PathVariable("type")String type, Model model) throws Exception{
		
		//model.addAttribute("cri",cri); //modelAttribute를 쓰기 때문에 이쪽은 필요없다
		model.addAttribute("type",type);
		
		return "employeeReportView";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//파일 보내주면 저장하기
	private String saveFile(MultipartFile file, String old_fileName, String id) throws Exception{
		if(file==null || file.isEmpty()) {
			if(old_fileName == null || old_fileName.isEmpty()) {
				File oldFile = new File(employeeAttachpath + File.separator+id, old_fileName);
				if(oldFile.exists()) oldFile.delete();
				return "";
			}
			return old_fileName;
		}
		
		//기존파일 삭제
		File oldFile = new File(employeeAttachpath + File.separator+id, old_fileName);
		if(oldFile.exists()) oldFile.delete();
		
		//신규 파일 저장
		String fileName = UUID.randomUUID().toString().replace("-", "") + "$$" + file.getOriginalFilename();
		File savePath = new File(employeeAttachpath + File.separator + id);
		
		if(!savePath.exists()) {
			savePath.mkdirs();
		}
		file.transferTo(new File(savePath,fileName));
		
		return old_fileName;
	}
	
}
