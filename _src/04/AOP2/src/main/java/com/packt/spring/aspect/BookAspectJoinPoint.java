package com.packt.spring.aspect;
import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
@Aspect
public class BookAspectJoinPoint {
    @Before("execution(public void com.journaldev.spring.model..set*(*))")
    public void loggingAdvice(JoinPoint joinPoint){
        System.out.println("<<BookAspectJoinPoint>>Before running "
        		+ "loggingAdvice on method="+joinPoint.toString());
        System.out.println("<<BookAspectJoinPoint>>Agruments Passed=" + 
        		Arrays.toString(joinPoint.getArgs()));
    }
    @Before("args(name)")
    public void logStringArguments(String name){
        System.out.println("<<BookAspectJoinPoint>>String argument "
        		+ "passed="+name);
    }
}
