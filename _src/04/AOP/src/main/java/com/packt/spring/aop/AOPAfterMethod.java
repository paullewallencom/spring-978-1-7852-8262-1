package com.packt.spring.aop;

import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;

public class AOPAfterMethod implements AfterReturningAdvice
{
	@Override
	public void afterReturning(Object returnValue, Method method,
		Object[] args, Object target) throws Throwable {
	        System.out.println("AOPAfterMethod : After method Captured!");
	}
}
