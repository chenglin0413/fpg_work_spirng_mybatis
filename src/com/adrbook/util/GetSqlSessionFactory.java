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
    * 私有構造方法，使該類不可創建新對象
    */
   private GetSqlSessionFactory(){

   }

   /**
    * 使用同步鎖
    * @return sql session 工廠
 * @throws IOException
    */
   synchronized public static SqlSessionFactory getSqlSessionFactory() {
       if (sqlSessionFactory == null){
	    	//使用sqlSessionFactory取得連線資訊。
	   		//1. 讀取配置文件
    	   	String resource = "mybatis-config.xml";
	   		InputStream is=null;
			try {
				is = Resources.getResourceAsStream(resource);
				//2. 創建sqlSessionFactory 工廠
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
