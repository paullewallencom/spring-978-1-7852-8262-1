package com.packt.spring.jaas.security;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.authentication.jaas.JaasGrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class SecureController {

	@RequestMapping(value="/admin/index")

	public String getAdmin(ModelMap model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		JaasGrantedAuthority jaasGrantedAuthority = (JaasGrantedAuthority)(auth.getAuthorities().toArray()[0]);
		
		UserPrincipal userPrincipal = (UserPrincipal)jaasGrantedAuthority.getPrincipal();
		userPrincipal.setRole(jaasGrantedAuthority.getAuthority());
		
		model.addAttribute("userPrincipal", userPrincipal);
		return "admin/index";
	}
	
	@RequestMapping(value="/enduser/index")

	public String getEnduser(ModelMap model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		JaasGrantedAuthority jaasGrantedAuthority = (JaasGrantedAuthority)(auth.getAuthorities().toArray()[0]);
		
		UserPrincipal userPrincipal = (UserPrincipal)jaasGrantedAuthority.getPrincipal();
		userPrincipal.setRole(jaasGrantedAuthority.getAuthority());
		
		model.addAttribute("userPrincipal", userPrincipal);
		return "enduser/index";
	}

}
