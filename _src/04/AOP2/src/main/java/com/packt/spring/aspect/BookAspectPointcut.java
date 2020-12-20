package com.packt.spring.aspect;
import org.aspectj.lang.annotation.*;
@Aspect
public class BookAspectPointcut {
     @Before("getNamePointcut()")
    public void loggingAdvice(){
        System.out.println("<<BookAspectPointcut>>Executing loggingAdvice on getName()");
    }
     @Before("getNamePointcut()")
    public void secondAdvice(){
        System.out.println("<<BookAspectPointcut>>Executing secondAdvice on getName()");
    }
     @Pointcut("execution(public String getName())")
    public void getNamePointcut(){}
      @Before("allMethodsPointcut()")
    public void allServiceMethodsAdvice(){
        System.out.println("<<BookAspectPointcut>>Before executing service method");
    }
     @Pointcut("within(com.packt.spring.aspect.*)")
    public void allMethodsPointcut(){}
     
}
