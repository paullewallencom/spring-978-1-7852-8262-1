package com.packt.spring.aop.service;

import com.packt.spring.aop.type.Role;
import com.packt.spring.aop.user.UserHolder;

public interface UserService {
	UserHolder getCurrentUser();

	void setCurrentUser(UserHolder userHolder);

	Role getUserRole();
}
