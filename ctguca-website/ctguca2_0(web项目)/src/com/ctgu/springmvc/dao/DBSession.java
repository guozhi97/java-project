package com.ctgu.springmvc.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBSession {
	private static SqlSessionFactory factory=null;
	static {
		if(factory==null){
			try {
				Reader reader = Resources.getResourceAsReader("com/ctgu/springmvc/daoconfig/Configuration.xml");
				factory = new SqlSessionFactoryBuilder().build(reader);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static SqlSession getSession(){
//		System.out.println("getSession");
		return factory.openSession();
	}
}
