package com.packt.spring.aop.interceptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import com.packt.spring.aop.annotation.SecurityAnnotation;
import com.packt.spring.aop.service.UserService;
import com.packt.spring.aop.type.Role;
import com.packt.spring.aop.ui.UIComponent;
@Aspect
public class SecurityInterceptor {
	public SecurityInterceptor() {		System.out.println("Security Interceptor created");	}
	@Autowired
	private UserService userService;
	@Pointcut("execution(com.packt.spring.aop.ui.UIComponent com.packt.spring.aop.ui.UIFactory.createComponent(..))")
	private void getComponent(ProceedingJoinPoint thisJoinPoint) {
	}
	@Around("getComponent(thisJoinPoint)")
	public UIComponent checkSecurity(ProceedingJoinPoint thisJoinPoint) throws Throwable {
		System.out.println("Intercepting creation of a component");
		Object[] arguments = thisJoinPoint.getArgs();
		if (arguments.length == 0) {			return null;	}
		Annotation annotation = checkTheAnnotation(arguments);
		boolean atrAccessSecurityAnnotationPresent = (annotation != null);

		if (atrAccessSecurityAnnotationPresent) {
			boolean userHasRole = verifyRole(annotation);
			if (!userHasRole) {
				System.out.println("Current user doesn't have permission to have this component created");
				return null;			}
		}
		System.out.println("Current user has required permissions for creating a component");
		return (UIComponent) thisJoinPoint.proceed();
	}
	private Annotation checkTheAnnotation(Object[] arguments) {
		Object concreteClass = arguments[0];
		AnnotatedElement annotatedElement = (AnnotatedElement) concreteClass;
		Annotation annotation = annotatedElement.getAnnotation(SecurityAnnotation.class);
		return annotation;	}
	private boolean verifyRole(Annotation annotation) {
		System.out.println("Security annotation is present so checking if the user can use it");
		SecurityAnnotation annotationRule = (SecurityAnnotation) annotation;
		List<Role> requiredRolesList = Arrays.asList(annotationRule.allowedRole());
		Role userRole = userService.getUserRole();
		boolean userHasRole = requiredRolesList.contains(userRole);		return userHasRole;	}}