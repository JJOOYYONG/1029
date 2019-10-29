package com.exam.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBManager {
	


	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		
		//자바7이상 부터 try with resources 문법제공. try 블록 끝나면 자동으로 close() 호출해서 자원 닫아줌
				try(InputStream is = Resources.getResourceAsStream("mybatis_config.xml")) {
				
					sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
	}//static
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	

	
	
}