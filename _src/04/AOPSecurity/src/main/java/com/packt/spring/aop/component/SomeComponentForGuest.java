package com.packt.spring.aop.component;

import com.packt.spring.aop.annotation.SecurityAnnotation;
import com.packt.spring.aop.type.Role;
import com.packt.spring.aop.ui.UIComponent;

@SecurityAnnotation(allowedRole = { Role.GUEST })
public class SomeComponentForGuest extends UIComponent {

	public SomeComponentForGuest() {
		this.componentName = "SomeComponentForGuest";
	}

	public static UIComponent getComponent() {
		return new SomeComponentForGuest();
	}
}
