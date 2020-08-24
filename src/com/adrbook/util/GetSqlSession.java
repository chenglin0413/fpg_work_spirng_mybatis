package com.adrbook.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class GetSqlSession {
	static SqlSession sqlSession =null;

	public GetSqlSession() {
		this.sqlSession=sqlSession;
	}

	public static SqlSession getSqlSession(){
		sqlSession = GetSqlSessionFactory.getSqlSessionFactory().openSession();
		return sqlSession;
	}
	public void destory(){
		sqlSession.commit();
	    sqlSession.close();
	}
}
