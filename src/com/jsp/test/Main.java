package com.jsp.test;

public class Main {
	
	public Main() {
		
		Parent parent = Parent.getInstance();
		
		String result = "";
		
		try {
			result = parent.resultSum(6, 7);
		} catch (Exception e) {
			System.out.println("오류가 발생했습니다.");
			e.printStackTrace();
		}
		
		System.out.println(result);
	}
	
}
