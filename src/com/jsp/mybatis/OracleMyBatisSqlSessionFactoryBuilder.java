package com.jsp.mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class OracleMyBatisSqlSessionFactoryBuilder extends SqlSessionFactoryBuilder {
	
	private OracleMyBatisSqlSessionFactoryBuilder() {}
	
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		
		String config = "com/jsp/mybatis/SqlConfig.xml";
		
		try {
			Reader reader = Resources.getResourceAsReader(config);
			
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
			reader.close();
			
			System.out.println("sqlSessionFactory 성공했습니다.");
			
		}catch(Exception e) {
			System.out.println("sqlSessionFactory 실패했습니다.");
			e.printStackTrace();
		}
		
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
