package com.packt.spring.aspect;
import org.aspectj.lang.annotation.*;
 @Aspect
public class BookAspect {
     @Before("execution(public String getName())")
    public void getNameAdvice(){
        System.out.println("<<BookAspect>>Executing Advice on getName()");
    }
        @Before("execution(* com.packt.spring.aspect.*.get*())")
    public void getAllAdvice(){
        System.out.println("<<BookAspect>>Service method getter called");
    }
}
