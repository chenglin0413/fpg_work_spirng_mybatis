package com.adrbook.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class GetSqlSessionFactory {


   private static SqlSessionFactory sqlSessionFactory;

   /**
    * �p���c�y��k�A�ϸ������i�Ыطs��H
    */
   private GetSqlSessionFactory(){

   }

   /**
    * �ϥΦP�B��
    * @return sql session �u�t
 * @throws IOException
    */
   synchronized public static SqlSessionFactory getSqlSessionFactory() {
       if (sqlSessionFactory == null){
	    	//�ϥ�sqlSessionFactory���o�s�u��T�C
	   		//1. Ū���t�m���
    	   	String resource = "mybatis-config.xml";
	   		InputStream is=null;
			try {
				is = Resources.getResourceAsStream(resource);
				//2. �Ы�sqlSessionFactory �u�t
		   		SqlSessionFactoryBuilder build = new SqlSessionFactoryBuilder();
		   		sqlSessionFactory = build.build(is);
		   		is.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("SqlSessiongFactoyr Error: "+e.toString());
			}finally{

			}
       }
       return sqlSessionFactory;
   }

}
