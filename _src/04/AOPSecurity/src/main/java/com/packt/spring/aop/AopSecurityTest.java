package com.packt.spring.aop;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.packt.spring.aop.component.*;
import com.packt.spring.aop.service.UserService;
import com.packt.spring.aop.type.Role;
import com.packt.spring.aop.ui.UIFactory;
import com.packt.spring.aop.user.UserHolder;

public class AopSecurityTest {
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("AOPSecurity.xml");
		UIFactory uiFactory = context.getBean(UIFactory.class);
		UserService userService = context.getBean(UserService.class);
		userService.setCurrentUser(new UserHolder(Role.READER));
	//	Assert.assertNotNull(uiFactory.createComponent(SomeComponentForAdmin.class));
	//	Assert.assertNull(uiFactory.createComponent(SomeComponentForGuest.class));
		Assert.assertNull(uiFactory.createComponent(SomeComponentForWriter.class));
	}
}
