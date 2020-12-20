package com.packt.spring.aop.component;

import com.packt.spring.aop.annotation.SecurityAnnotation;
import com.packt.spring.aop.type.Role;
import com.packt.spring.aop.ui.UIComponent;

@SecurityAnnotation(allowedRole = { Role.WRITER,Role.READER })
public class SomeComponentForWriter extends UIComponent {

	public SomeComponentForWriter() {
		this.componentName = "SomeComponentForWriter";
	}

	public static UIComponent getComponent() {
		return new SomeComponentForWriter();
	}
}
