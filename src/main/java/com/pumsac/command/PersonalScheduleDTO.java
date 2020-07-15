package com.pumsac.command;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.pumsac.vo.PersonalScheduleVO;

public class PersonalScheduleDTO {
	private int no;
	private int projectId;
	private String title;
	private String content;
	private Date startDate;
	private Date endDate;
	private String color;
	private int processPercent;
	private String processStaues;
	private String approvalYn;
	private String charger;
	private String[] memberId;
	public PersonalScheduleDTO() {
		super();
	}
	public PersonalScheduleDTO(int no, int projectId, String title, String content, Date startDate, Date endDate,
			String color, int processPercent, String processStaues, String apporvalyn, String charger,
			String[] memberId) {
		super();
		this.no = no;
		this.projectId = projectId;
		this.title = title;
		this.content = content;
		this.startDate = startDate;
		this.endDate = endDate;
		this.color = color;
		this.processPercent = processPercent;
		this.processStaues = processStaues;
		this.approvalYn = apporvalyn;
		this.charger = charger;
		this.memberId = memberId;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getStartDate() {
		return startDate;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getProcessPercent() {
		return processPercent;
	}
	public void setProcessPercent(int processPercent) {
		this.processPercent = processPercent;
	}
	public String getProcessStaues() {
		return processStaues;
	}
	public void setProcessStaues(String processStaues) {
		this.processStaues = processStaues;
	}
	public String getApprovalYn() {
		return approvalYn;
	}
	public void setApprovalYn(String approvalYn) {
		this.approvalYn = approvalYn;
	}
	public String getCharger() {
		return charger;
	}
	public void setCharger(String charger) {
		this.charger = charger;
	}
	public String[] getMemberId() {
		return memberId;
	}
	public void setMemberId(String[] memberId) {
		this.memberId = memberId;
	}
	public List<PersonalScheduleVO> ToPersonalScheduleVO(PersonalScheduleDTO psd) {
		List<PersonalScheduleVO> psvList = new ArrayList<PersonalScheduleVO>();
		for (int i = 0; i < psd.getMemberId().length; i++) {
			PersonalScheduleVO psv = new PersonalScheduleVO();
			psv.setNo(psd.getNo());
			psv.setProjectId(psd.getProjectId());
			psv.setTitle(psd.getTitle());
			psv.setContent(psd.getContent());
			psv.setStartDate(psd.getStartDate());
			psv.setEndDate(psd.getEndDate());
			psv.setColor(psd.getColor());
			psv.setCharger(psd.getCharger());
			psv.setProcessPercent(psd.getProcessPercent());
			psv.setProcessStaues(psd.getProcessStaues());
			psv.setApprovalYn(psd.getApprovalYn());
			psv.setMemberId(psd.getMemberId()[i]);
			
			psvList.add(psv);
		}
		return psvList;
	}
}
