package com.pumsac.dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/pumsac/context/root-context.xml")
public class TestBasicDataSource {
	
	@Autowired
	private BasicDataSource dataSource;
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	@Before
	public void init() throws SQLException{
		conn = dataSource.getConnection();
	}
	/*
	//test와 assert는 세트처럼 쓰인다. 결과 success와 fail에 메소드명이 나온다
	//테스트 할 때는 1.퍼블릭이고  2.리턴타입이 없고  3.파라미터가 없어야 한다
	@Test
	public void testConnection() throws SQLException{
		
		Connection conn = this.conn;
		
		Assert.assertNotEquals(null, conn);
	}
	*/
	@After
	public void end() throws SQLException{
		if(rs!= null)rs.close();
		if(stmt!=null)stmt.close();
		if(conn!=null)conn.close();
	}
	
	
	
}
