package com.test.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.groupware.dao.board.PageBoardDAO;
import com.groupware.dao.board.ReplyDAO;
import com.groupware.dao.employee.CareerDAO;
import com.groupware.dao.employee.DepartmentDAO;
import com.groupware.dao.employee.EmployeeDAO;
import com.groupware.dao.employee.PositionDAO;
import com.groupware.dao.menu.MenuDAO;
import com.groupware.dto.MenuVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/com/groupware/context/root-context.xml")
@TransactionConfiguration(transactionManager="transactionManager")
//@TransactionConfiguration(transactionManager="transactionManager",defaultRollback="false")
@Transactional
public class TestMemberDAOImpl {
	
	//test에서 transaction은 무조건 롤백.
	@Autowired
	private MenuDAO menuDAO;
	@Autowired
	private PageBoardDAO boardDAO;
	@Autowired
	private ReplyDAO replyDAO;
	@Autowired
	private CareerDAO careerDAO;
	@Autowired
	private EmployeeDAO employeeDAO;
	@Autowired
	private DepartmentDAO deptDAO;
	@Autowired
	private PositionDAO positionDAO;
	
	
	
	@Test
	//@Rollback
	public void testSelectMenu() throws SQLException{
		
		List<MenuVO> list = menuDAO.selectMenuList();
		
		Assert.assertNotEquals(list, null);
	}
	
	/*
	@Test
	public void testInsertMember() throws SQLException{
		
		MemberVO member = new MemberVO();
		member.setId("haha");
		member.setName("haha");
		member.setPwd("haha");
		member.setEmail("hahah@hah.com");
		member.setPhone("19120234123");
		member.setPicture("bengbeng.jpg");
		
		memberDAO.insertMember(member);
		
		MemberVO result = memberDAO.selectMemberById("haha");
		
		Assert.assertEquals(member.getId(), result.getId());
		
	}
	*/
	
	
}















