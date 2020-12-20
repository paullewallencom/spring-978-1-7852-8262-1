package com.packt.spring.aop.component;

import com.packt.spring.aop.annotation.SecurityAnnotation;
import com.packt.spring.aop.type.Role;
import com.packt.spring.aop.ui.UIComponent;

@SecurityAnnotation(allowedRole = { Role.ADMIN, Role.GUEST })
public class SomeComponentForAdminAndGuest extends UIComponent {

	public SomeComponentForAdminAndGuest() {
		this.componentName = "SomeComponentForAdmin";
	}

	public static UIComponent getComponent() {
		return new SomeComponentForAdminAndGuest();
	}
}
