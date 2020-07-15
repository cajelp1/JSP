package com.jsp.test;

public class Parent {
	
	//의존주입을 할 것이기에 여기에서는 아무것도 쓰지 않음
	// 전체는 발상적 사고
	// 기능은 순차적
	// 클래스는 귀납적
	
	private Child child;	//여기서만 쓰지만 바깥에서 주입할 통로를 만들어야.
							//흐럅! 겟터셋터!

	public Child getChild() {
		return child;
	}
	public void setChild(Child child) {
		this.child = child;
	}
	
	//그리고 여기서 기능할 부분?
	public String resultSum(int a, int b) throws Exception {
		String resultSum = "";
		int result = child.sum(a, b);
		
		resultSum = a+"와 "+b+"의 합은 "+result;
		
		return resultSum;
	}
	//child interface가 있으니 무리없이 parent가 구현할 수 있다.
	
	//그리구 메인에서 부르기 싫어? 그럼 싱글톤 ㄱㄱ
	
	private static Parent instance = new Parent();
	private Parent() {};
	public static Parent getInstance() {
		return instance;
	}
	
}
