package com.packt.spring.aop;
import java.util.Arrays;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
public class AOPAroundMethod implements MethodInterceptor {
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {

	System.out.println("Method name : "	+ methodInvocation.
			getMethod().getName());
	System.out.println("Method arguments : "+ Arrays.
			toString(methodInvocation.getArguments()));
	System.out.println("AOPAroundMethod : Before method Captured!");
		try {
			Object result = methodInvocation.proceed();
			System.out.println("AOPAroundMethod : Before after Captured!");
			return result;
		} catch (IllegalArgumentException e) {
			System.out.println("AOPAroundMethod : Throw exception Captured!");
			throw e;
		}
	}
}
