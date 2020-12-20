package com.packt.spring.aspect;
import org.aspectj.lang.ProceedingJoinPoint;
public class BookXMLConfigAspect {
   public Object BookAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
       System.out.println("<<BookXMLConfigAspect>>Before "
       		+ "invoking getName() method");
       Object value = null;
       try {
           value = proceedingJoinPoint.proceed();
       } catch (Throwable e) {           e.printStackTrace();       }
       System.out.println("<<BookXMLConfigAspect>> After "
       		+ "invoking getName() method. Return value="+value);
       return value;
   }
}
