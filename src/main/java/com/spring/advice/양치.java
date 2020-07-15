package com.spring.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class 양치 {
	
	public void chikachikaProceed(ProceedingJoinPoint jp) throws Throwable{
		
		Object result = jp.proceed();
		
		System.out.println("치카치카");
		
	}
	
	public void chikachika(JoinPoint jp) throws Throwable{
		
		System.out.println("치카치카");
		
	}
	
}
