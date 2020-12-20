package com.packt.spring.aop;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

public class AOPBeforeMethod implements MethodBeforeAdvice
{
	@Override
	public void before(Method method, Object[] args, Object target)
		throws Throwable {
	        System.out.println("AOPBeforeMethod : Before method Captured!");
	}
}
