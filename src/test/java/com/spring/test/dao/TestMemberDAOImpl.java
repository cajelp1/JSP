package com.spring.test.dao;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.MemberDAO;
import com.spring.dto.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/com/spring/context/root-context.xml")
@TransactionConfiguration(transactionManager="transactionManager")
//@TransactionConfiguration(transactionManager="transactionManager",defaultRollback="false")
@Transactional
public class TestMemberDAOImpl {
	
	//test에서 transaction은 무조건 롤백.
	@Autowired
	private MemberDAO memberDAO;
	
	@Test
	//@Rollback
	public void testSelectMember() throws SQLException{
		String id = "admin";
		
		MemberVO member = memberDAO.selectMemberById(id);
		
		Assert.assertEquals(id, member.getId());
	}
	
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
	
}















