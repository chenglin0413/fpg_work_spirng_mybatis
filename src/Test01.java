

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adrbook.model.AdrbookDaoIMP;
import com.adrbook.model.AdrbookVO;

public class Test01 {
	private  InputStream is ;
	private  SqlSession sqlSession ;
	private  AdrbookDaoIMP abkDao ;

	@Test
	public void test() {
				//初始化 spring
				ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
				//將資料裝進VO
				AdrbookVO adk_vo = new AdrbookVO();
		    	String xuid = "200000065";
		    	adk_vo.setName("ABC");
		    	adk_vo.setTel("123456");
		    	adk_vo.setGender("male");
		    	adk_vo.setType("同學");
		    	adk_vo.setType_index(0);
		    	adk_vo.setNotes("test");
		    	//adk_vo.setXuid(xuid);
		    	adk_vo.setIp("0:0:0:0:0:0:0:1");
		    	adk_vo.setBrow_ver("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36");
		    	abkDao = (AdrbookDaoIMP) applicationContext.getBean("adrbookDaoIMP");
		    	//abkDao.update(adk_vo);
		    	abkDao.insert(adk_vo);
				List<AdrbookVO> adks =abkDao.getAll();
				for(AdrbookVO adk :adks){
					System.out.println(adk);
				}
				((ConfigurableApplicationContext) applicationContext).close();
	}

}
