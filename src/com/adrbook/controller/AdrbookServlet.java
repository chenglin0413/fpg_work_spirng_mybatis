package com.adrbook.controller;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.adrbook.model.AdrbookDaoIMP;
import com.adrbook.model.AdrbookVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;



@WebServlet("/AdrbookServlet/*")

public class AdrbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdrbookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8"); // 解決中文字轉入問題
		String brow_ver = request.getHeader("User-agent");//取得廠商版本號、類型
		String ip = request.getRemoteAddr();//取得廠商的ip地址
		//初始化 spring
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		Map dataMap = new Gson().fromJson(request.getReader(), Map.class);//將頁面json字串轉成物件
		String action = dataMap.get("action").toString(); // 動作邏輯判斷 : action
		AdrbookVO adk_vo = new AdrbookVO();
		// spring 讀取 applicationContext.xml 創建實體 abkDao
		AdrbookDaoIMP abkDao = (AdrbookDaoIMP) applicationContext.getBean("adrbookDaoIMP");//DaoIMP的類名，預設首字小寫

        if("getAll".equals(action)){//全部搜尋

        	List<AdrbookVO> abks = abkDao.getAll();
    	    String json = new Gson().toJson(abks);
    	    response.setContentType("application/json");
    	    response.setContentType("text/plain");
    	    response.setCharacterEncoding("UTF-8");
    	    response.getWriter().write(json);
        }
        if("insert".equals(action)){// 新增一筆
        	Map insert_data =  (Map) dataMap.get("data");

        	adk_vo.setName(insert_data.get("name").toString());
        	adk_vo.setTel(insert_data.get("tel").toString());
        	adk_vo.setNotes(insert_data.get("notes").toString());
        	adk_vo.setType(insert_data.get("type").toString());
        	adk_vo.setType_index(Integer.parseInt(insert_data.get("type_index").toString()));
        	adk_vo.setGender(insert_data.get("gender").toString());
        	adk_vo.setIp(ip);
        	adk_vo.setBrow_ver(brow_ver);
        	//insert
        	abkDao.insert(adk_vo);
        	System.out.println("insert__complete");
        	System.out.println(insert_data);
        }
        if("update".equals(action)){// 更新一筆
        	Map update_data =  (Map) dataMap.get("data");

        	adk_vo.setName(update_data.get("name").toString());
        	adk_vo.setTel(update_data.get("tel").toString());
        	adk_vo.setGender(update_data.get("gender").toString());
        	adk_vo.setType(update_data.get("type").toString());
        	adk_vo.setType_index(Integer.parseInt(update_data.get("type_index").toString()));
        	adk_vo.setNotes(update_data.get("notes").toString());
        	adk_vo.setXuid(update_data.get("xuid").toString());
        	adk_vo.setIp(ip);
        	adk_vo.setBrow_ver(brow_ver);
        	System.out.println(adk_vo);
        	//update
        	abkDao.update(adk_vo);
        	System.out.println("update__complete");
        	System.out.println(update_data);
        }
        if("delete".equals(action)){// 刪除一筆
        	Map delete_data =  (Map) dataMap.get("data");
        	String xuid = delete_data.get("xuid").toString();
        	//delete
        	abkDao.delete(xuid);
        	System.out.println("del_complete,xuid : "+xuid);
        }
        ((ConfigurableApplicationContext) applicationContext).close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
