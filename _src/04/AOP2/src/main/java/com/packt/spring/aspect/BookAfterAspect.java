package com.packt.spring.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
@Aspect
public class BookAfterAspect {
     @After("args(name)")
    public void logStringArguments(String name){
        System.out.println("<<BookAfterAspect>>Running After Advice. "
        		+ "String argument passed="+name);
    }
     @AfterThrowing("within(com.packt.spring.model.Book)")
    public void logExceptions(JoinPoint joinPoint){
        System.out.println("<<BookAfterAspect>>Exception thrown in Book Method="
    +joinPoint.toString());
    }
     @AfterReturning(pointcut="execution(* getName())", returning="returnString")
    public void getNameReturningAdvice(String returnString){
        System.out.println("<<BookAfterAspect>>getNameReturningAdvice executed. "
        		+ "Returned String="+returnString);
    }
}
