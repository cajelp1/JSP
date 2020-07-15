package com.jsp.listener;

import java.lang.reflect.Method;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dao.MemberDao;
import com.jsp.service.MemberServiceImpl;

@WebListener
public class InitListener implements ServletContextListener {

    public InitListener() {
        
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
        
    	String sqlFactoryType = arg0.getServletContext().getInitParameter("sessionFactory");
    	String daoType = arg0.getServletContext().getInitParameter("memDao");
    	
    	try {
    		
    		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory)Class.forName(sqlFactoryType).newInstance();
    		
    		//그리고 dao에 SqlSessionFactory 를 setting해야하는데...
    		//해당 setter는 memberDao에는 없지만 memberDaoImpl에는 있어야 하는 메소드다. 
    		
    		//상속받는 자식 클래스 중에 있을수도 있고 없을수도 있는 메소드이므로... 와일드카드! reflection 등!장!
    		
    		Class<?> cls = Class.forName(daoType);	//daoType의 클래스들(상속클래스포함) 중에서
    		
    		Method setSqlSessionFactory = cls.getMethod("setSessionFactory", SqlSessionFactory.class);
    		//메소드를 찾는데 해당 매소드는 (메소드명, 파라미터이름) 으로 작성.
    		//class.getMethod(String name, Class<?>...parameterTypes)
    		
    		Object obj = cls.newInstance();
    		setSqlSessionFactory.invoke(obj, sqlSessionFactory);	//어... 이런 메소드를 가진 클래스오브젝트를 찾아서 알아서 해봐라!?
    		
    		MemberDao memDao = (MemberDao)obj;
    		
    		MemberServiceImpl.getInstance().setMemDao(memDao);
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
	
}
