package com.packt.spring.aop.annotation;

import java.lang.annotation.*;

import com.packt.spring.aop.type.Role;

@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityAnnotation {
	Role[] allowedRole();
}
