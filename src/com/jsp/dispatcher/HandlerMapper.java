package com.jsp.dispatcher;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import com.jsp.action.Action;
import com.jsp.action.ApplicationContext;

public class HandlerMapper {
	
	//인스턴스 생성 불가능.
	//헌데 결합도 문제로 FrontServlet에서 호출해서 쓸 것임. 고로 생성자를 잠그면 안 됨.
	//private HandlerMapper() {}
	
	//리스너에 넣지 않는 이유? 서블릿은 톰캣이 따로 자기가 복사해서 쓰기 때문에 주입이 안된다...
	//그래서 수동으로 만들어서 주입해주기로..?
	
	//
	
	private Map<String, Action> commandMap = new HashMap<String, Action>();
	
	{
		
		//리소스 번들은 뒤에 자동으로 .properties를 붙이기때무네 path를 지정할 때 확장자 properties를 안 쓴다
		String path ="com/jsp/properties/url";
		
		ResourceBundle rbHome = ResourceBundle.getBundle(path);
		
		Set<String> actionSetHome = rbHome.keySet();
		
		Iterator<String> it = actionSetHome.iterator();
		
		while(it.hasNext()) {
			String command = it.next();
			String actionClassName = rbHome.getString(command);
			System.out.println(actionClassName);
			
			try {
				
				Class<?> actionClass = Class.forName(actionClassName);
				Action commandAction = (Action)actionClass.newInstance();
				
				//의존성 확인 및 조립
				Method[] methods = actionClass.getMethods();
				
				for(Method method : methods){
					if(method.getName().contains("set")) {
						
						String paramType = method.getParameterTypes()[0].getName();
						paramType = paramType.substring(paramType.lastIndexOf(".")+1);
						
						paramType=(paramType.charAt(0) + "").toLowerCase()+paramType.substring(1);
						
						try {
							method.invoke(commandAction, 
									ApplicationContext.getApplicationContext().get(paramType));
							
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
				
				commandMap.put(command, commandAction);
				
				System.out.println("[HandlerMapper]"+command+":"+commandAction+" 준비되었습니다.");
				
			}catch(ClassNotFoundException e) {
				System.out.println("[HandlerMapper]"+actionClassName + "이 존재하지 않습니다.");
			}catch(InstantiationException e) {
				System.out.println("[HandlerMapper]"+actionClassName + "인스턴스를 생성할 수 없습니다.");
			}catch(IllegalAccessException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public Action getAction(String command){
		Action action = commandMap.get(command);
		return action;
	}
}
