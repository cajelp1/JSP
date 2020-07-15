package com.pumsac.dataSource;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pumsac.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/com/pumsac/context/root-context.xml")
public class TestSqlSession {
	
	@Autowired
	private SqlSession session;
	
	@Before
	public void init(){}
	/*
	@Test
	public void testSession(){
		Assert.assertNotEquals(null, session);
	}
	*/
	@Test
	public void testSelectMember()throws SQLException{
		
		String email="admin@admin.com";
		
		MemberVO member = session.selectOne("member-mapper.selectMemberById",email);
		
		Assert.assertEquals(email, member.getEmail());
		
		System.out.println(member.getRegdate());
		System.out.println(member.getDetailAddress());
	}
	
	
}
