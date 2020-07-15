package com.java.general;

public class TripleSummation extends Summation{

	private int c;

	@Override
	public int sum() {
		int result = super.sum()+c;
		return result;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}
	
}
