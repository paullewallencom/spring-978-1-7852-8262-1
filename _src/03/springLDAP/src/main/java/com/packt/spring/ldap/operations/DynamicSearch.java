package com.packt.spring.ldap.operations;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

import com.packt.spring.ldap.LDAPUser;
import com.packt.spring.ldap.UserAttributesMapper;

public class DynamicSearch {

	private LdapTemplate ldapTemplate;
	
	@SuppressWarnings("unchecked")
	public Set<LDAPUser> getAllUsers(String surName){
				
		UserAttributesMapper mapper = new UserAttributesMapper();
		
		AndFilter filterObject = new AndFilter();
		filterObject.and(new EqualsFilter("objectClass", "person"));
		filterObject.and(new EqualsFilter("sn", surName));
		
		return new HashSet<LDAPUser>(
			ldapTemplate.search("ou=users,ou=system", filterObject.encode(), mapper));
	}

	public void setLdapTemplate(LdapTemplate ldapTemplate){
		this.ldapTemplate = ldapTemplate;
	}
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("packtldap.xml");
	
		DynamicSearch dynamicSearch = new DynamicSearch();
		dynamicSearch.setLdapTemplate(context.getBean("ldapTemplate", LdapTemplate.class));
		for (LDAPUser user : dynamicSearch.getAllUsers("David")){
			System.out.println(user);
		}
	}

}
