package com.jsp.mybatis;

import java.io.Reader;
import java.sql.Connection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;

public class OracleMyBatisSqlSessionFactory implements SqlSessionFactory {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		
		String config = "com/jsp/mybatis/SqlConfig.xml";
		
		try {
			Reader reader = Resources.getResourceAsReader(config);
			
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
			reader.close();
			
			System.out.println("sqlSessionFactory 생성에 성공했습니다.");
			
		}catch(Exception e) {
			System.out.println("sqlSessionFactory 생성에 실패했습니다.");
			e.printStackTrace();
		}
		
	}
	

	@Override
	public Configuration getConfiguration() {
		return sqlSessionFactory.getConfiguration();
	}

	@Override
	public SqlSession openSession() {
		return sqlSessionFactory.openSession();
	}

	@Override
	public SqlSession openSession(boolean autoCommit) {
		return sqlSessionFactory.openSession(autoCommit);
	}

	@Override
	public SqlSession openSession(Connection conn) {
		return sqlSessionFactory.openSession(conn);
	}

	@Override
	public SqlSession openSession(TransactionIsolationLevel transLevel) {
		return sqlSessionFactory.openSession(transLevel);
	}

	@Override
	public SqlSession openSession(ExecutorType execType) {
		return sqlSessionFactory.openSession(execType);
	}

	@Override
	public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
		return sqlSessionFactory.openSession(execType, autoCommit);
	}

	@Override
	public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel transLevel) {
		return sqlSessionFactory.openSession(execType, transLevel);
	}

	@Override
	public SqlSession openSession(ExecutorType execType, Connection conn) {
		return sqlSessionFactory.openSession(execType, conn);
	}

}
