package com.packt.spring.aspect;
import org.aspectj.lang.annotation.*;
@Aspect
public class BookAnnotationAspect {
   @Before("@annotation(com.packt.spring.aspect.Loggable)")
   public void myAdvice(){
       System.out.println("<<BookAnnotationAspect>>Executing myAdvice!!");
   }
}
