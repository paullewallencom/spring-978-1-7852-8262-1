package com.packt.spring.aop.user;

import com.packt.spring.aop.type.Role;

public class UserHolder {
	private Role userRole;

	public UserHolder(Role userRole) {
		this.userRole = userRole;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

}
