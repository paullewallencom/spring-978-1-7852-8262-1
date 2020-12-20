package com.packtpub.spring_soap_security.service.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		return getUserDataFromDao(username);
	}

	private MyUserDetail getUserDataFromDao(String username) {

		MyUserDetail mydetail=new MyUserDetail(username,"pass",true,true,true,true);
		mydetail.getAuthorities().add(new GrantedAuthorityImpl("ROLE_GENERAL_OPERATOR"));
		
		return mydetail;
		
	}

}
