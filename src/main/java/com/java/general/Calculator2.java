package com.java.general;

public class Calculator2 extends Calculator{
	
	private Summation summation; // = new Summation();
	public void setSummation(Summation summation) {
		this.summation = summation;
	}
	
	public void sum(int a, int b) {
		summation.setA(a);
		summation.setB(b);
		System.out.println("두 정수의 합 : " +summation.sum()+"!!!!!!!!!!!!!!!!!!!!!!");
	}
	
	public void sum(int a, int b, int c) {
		if(summation instanceof TripleSummation) {
			((TripleSummation) summation).setC(c);
			int result = summation.sum();
			System.out.println("세 정수의 합 : "+result+"!!!!!!!!!!!!!!!!!!!!!!!");
		}else {
			System.out.println("세 정수의 합은 제공되지 않습니다!!!!!!!!!!!!!!!!!!!!!");
		}
	}
	
}
