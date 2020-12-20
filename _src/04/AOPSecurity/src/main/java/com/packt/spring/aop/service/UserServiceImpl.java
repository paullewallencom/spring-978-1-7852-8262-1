package com.packt.spring.aop.service;
import org.springframework.stereotype.Service;
import com.packt.spring.aop.type.Role;
import com.packt.spring.aop.user.UserHolder;
@Service
public class UserServiceImpl implements UserService {
	private UserHolder userHolder;
	@Override
	public UserHolder getCurrentUser() {
		return userHolder;
	}
	@Override
	public void setCurrentUser(UserHolder userHolder) {
		this.userHolder = userHolder;
	}
	@Override
	public Role getUserRole() {
		if (userHolder == null) {
			return null;
		}
		return userHolder.getUserRole();
	}
}
