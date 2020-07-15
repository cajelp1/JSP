package com.java.general;

public class GeneralMain {

	public static void main(String[] args) {
		
		Calculator cal = new Calculator();
		TripleSummation triSum = new TripleSummation();
		
		cal.setSummation(triSum);
		
		cal.sum(3, 5);
		cal.sum(4, 4, 2);
		
	}

}
