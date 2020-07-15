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
	
	private static SqlSessionFactory sqlSessionfactory;
	
	static {
		
		String config="com/jsp/mybatis/SqlConfig.xml";
		
		try {
			Reader reader = Resources.getResourceAsReader(config);
			
			sqlSessionfactory = new SqlSessionFactoryBuilder().build(reader);
			
			reader.close();
			
			System.out.println("sqlSessionFactory 성공했습니다.");
			
		}catch(Exception e){
			System.out.println("sqlSessionFactory 실패했습니다.");
			e.printStackTrace();
		}
		
	}
	

	@Override
	public Configuration getConfiguration() {
		return sqlSessionfactory.getConfiguration();
	}

	@Override
	public SqlSession openSession() {
		return sqlSessionfactory.openSession();
	}

	@Override
	public SqlSession openSession(boolean autoCommit) {
		return sqlSessionfactory.openSession(autoCommit);
	}

	@Override
	public SqlSession openSession(Connection conn) {
		return sqlSessionfactory.openSession(conn);
	}

	@Override
	public SqlSession openSession(TransactionIsolationLevel transLevel) {
		return sqlSessionfactory.openSession(transLevel);
	}

	@Override
	public SqlSession openSession(ExecutorType execType) {
		return sqlSessionfactory.openSession(execType);
	}

	@Override
	public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
		return sqlSessionfactory.openSession(execType, autoCommit);
	}

	@Override
	public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel transLevel) {
		return sqlSessionfactory.openSession(execType, transLevel);
	}

	@Override
	public SqlSession openSession(ExecutorType execType, Connection conn) {
		return sqlSessionfactory.openSession(execType, conn);
	}

}
