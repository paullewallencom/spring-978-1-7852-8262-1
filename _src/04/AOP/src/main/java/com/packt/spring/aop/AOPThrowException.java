package com.packt.spring.aop;

import org.springframework.aop.ThrowsAdvice;

public class AOPThrowException implements ThrowsAdvice {
	public void afterThrowing(IllegalArgumentException e) throws Throwable {
		System.out.println("AOPThrowException : Throw exception captured!");
	}
}
