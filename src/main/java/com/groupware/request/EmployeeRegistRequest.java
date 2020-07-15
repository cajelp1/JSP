package com.groupware.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.groupware.dto.CareerVO;
import com.groupware.dto.EmployeeVO;

public class EmployeeRegistRequest {
	
	private String id;
	private String pwd;
	private String name;
	private String[] ssn;
	private String phone_p;
	private String phone_c;
	private String[] email;
	private String postCode;
	private String address1;
	private String address2;
	private String register;
	
	private String posi_no;
	private String dept_no;
	private String eno;
	
	private int enabled=1;
	private String regDate;
	private String remark;
	
	private MultipartFile picture;
	
	private MultipartFile licenseDoc;
	private MultipartFile graduDoc;
	private MultipartFile scoreDoc;
	
	private String[] coName; //회사명
	private String[] dept; //부서
	private String[] position; //직위
	private String[] startDay; //입사일
	private String[] endDay;//퇴사일
	private String[] job; //주요업무
	
	
	public EmployeeVO getEmployeeVO() throws ParseException {
		EmployeeVO employee = new EmployeeVO();
		
		employee.setId(id);
		employee.setPwd(pwd);
		employee.setName(name);
		employee.setSsn(ssn[0]+ssn[1]);
		employee.setPhone_p(phone_p);
		employee.setPhone_c(phone_c);
		if(email.length<2) {
			employee.setEmail(email[0]);
		}else {
			employee.setEmail(email[0]+"@"+email[1]);
		}
		employee.setPostCode(postCode);
		employee.setAddress1(address1);
		employee.setAddress2(address2);
		employee.setRegister(register);
		employee.setEno(eno);
		employee.setEnabled(enabled);
		employee.setRegDate(new SimpleDateFormat("yyyy-MM-dd").parse(regDate));
		employee.setRemark(remark);
		
		employee.setDept_no(dept_no);
		employee.setPosi_no(posi_no);
		
		return employee;
	}
	
	public List<CareerVO> getCareerVO() throws ParseException{
		List<CareerVO> list = new ArrayList<CareerVO>();
		if(job!=null) {
			for(int i = 0; i < job.length; i++) {
				CareerVO career = new CareerVO();
				
				career.setCoName(coName[i]);
				career.setDept(dept[i]);
				career.setJob(job[i]);
				career.setPosition(position[i]);
				career.setStartDay(new SimpleDateFormat("yyyy-MM-dd").parse(startDay[i]));
				career.setEndDay(new SimpleDateFormat("yyyy-MM-dd").parse(endDay[i]));
				career.setId(id);
				
				list.add(career);
			}
		}
		return list;
	}
	
	@Override
	public String toString() {
		return "EmployeeRegistRequest [id=" + id + ", pwd=" + pwd + ", name=" + name + ", ssn=" + Arrays.toString(ssn)
				+ ", phone_p=" + phone_p + ", phone_c=" + phone_c + ", email=" + Arrays.toString(email) + ", postCode="
				+ postCode + ", address1=" + address1 + ", address2=" + address2 + ", register=" + register
				+ ", posi_no=" + posi_no + ", dept_no=" + dept_no + ", eno=" + eno + ", enabled=" + enabled
				+ ", regDate=" + regDate + ", remark=" + remark + ", picture=" + picture + ", licenseDoc=" + licenseDoc
				+ ", graduDoc=" + graduDoc + ", scoreDoc=" + scoreDoc + "]";
	}
	
	public String careerToString() {
		return Arrays.toString(coName)
				+Arrays.toString(dept)
				+Arrays.toString(position)
				+Arrays.toString(startDay)
				+Arrays.toString(endDay)
				+Arrays.toString(job);
	}
	
	
	
	
	
	public String[] getCoName() {
		return coName;
	}
	public void setCoName(String[] coName) {
		this.coName = coName;
	}
	public String[] getDept() {
		return dept;
	}
	public void setDept(String[] dept) {
		this.dept = dept;
	}
	public String[] getPosition() {
		return position;
	}
	public void setPosition(String[] position) {
		this.position = position;
	}
	public String[] getStartDay() {
		return startDay;
	}
	public void setStartDay(String[] startDay) {
		this.startDay = startDay;
	}
	public String[] getEndDay() {
		return endDay;
	}
	public void setEndDay(String[] endDay) {
		this.endDay = endDay;
	}
	public String[] getJob() {
		return job;
	}
	public void setJob(String[] job) {
		this.job = job;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getSsn() {
		return ssn;
	}
	public void setSsn(String[] ssn) {
		this.ssn = ssn;
	}
	public String getPhone_p() {
		return phone_p;
	}
	public void setPhone_p(String phone_p) {
		this.phone_p = phone_p;
	}
	public String getPhone_c() {
		return phone_c;
	}
	public void setPhone_c(String phone_c) {
		this.phone_c = phone_c;
	}
	public String[] getEmail() {
		return email;
	}
	public void setEmail(String[] email) {
		this.email = email;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public String getPosi_no() {
		return posi_no;
	}
	public void setPosi_no(String posi_no) {
		this.posi_no = posi_no;
	}
	public String getDept_no() {
		return dept_no;
	}
	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}
	public String getEno() {
		return eno;
	}
	public void setEno(String eno) {
		this.eno = eno;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public MultipartFile getPicture() {
		return picture;
	}
	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}
	public MultipartFile getLicenseDoc() {
		return licenseDoc;
	}
	public void setLicenseDoc(MultipartFile licenseDoc) {
		this.licenseDoc = licenseDoc;
	}
	public MultipartFile getGraduDoc() {
		return graduDoc;
	}
	public void setGraduDoc(MultipartFile graduDoc) {
		this.graduDoc = graduDoc;
	}
	public MultipartFile getScoreDoc() {
		return scoreDoc;
	}
	public void setScoreDoc(MultipartFile scoreDoc) {
		this.scoreDoc = scoreDoc;
	}
}
