package com.packt.spring.aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
@Aspect
public class BookAroundAspect {
     @Around("execution(* com.packt.spring.model.Book.getName())")
    public Object BookAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("<<BookAroundAspect>>Before invoking getName() "
        		+ "method");
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("<<BookAroundAspect>>After invoking getName() method. "
        		+ "Return value="+value);
        return value;
    }
}
