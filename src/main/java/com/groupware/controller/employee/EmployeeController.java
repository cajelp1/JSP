package com.groupware.controller.employee;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.groupware.dto.CareerVO;
import com.groupware.dto.EmployeeVO;
import com.groupware.exception.IdNotFoundException;
import com.groupware.request.EmployeeRegistRequest;
import com.groupware.request.SearchCriteria;
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
	
	@Resource(name="picture")
	private String picturePath;
	
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
	public void registPost(EmployeeRegistRequest request, HttpServletResponse response) throws Exception{
		
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
		
	}
	
	//로그인 체크
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
}
