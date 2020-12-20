package com.packt.spring.ldap.operations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;

import com.packt.spring.ldap.LDAPUser;

public class RemoveUser {

	private LdapTemplate ldapTemplate;
	
	public void remove(String commonName){
	
		String baseDn = "ou=users,ou=system";
		DistinguishedName distinguisedName = new DistinguishedName(baseDn);
		distinguisedName.add("cn", commonName);
			
		ldapTemplate.unbind(distinguisedName);
	}

	public void setLdapTemplate(LdapTemplate ldapTemplate){
		this.ldapTemplate = ldapTemplate;
	}
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("packtldap.xml");	
		LdapTemplate ldapTemplate = context.getBean("ldapTemplate", LdapTemplate.class);
		
		RemoveUser removePerson = new RemoveUser();
		removePerson.setLdapTemplate(ldapTemplate);
		removePerson.remove("New User");
		
		SimpleSearch simpleSearch = new SimpleSearch();
		simpleSearch.setLdapTemplate(ldapTemplate);
		for (LDAPUser user : simpleSearch.getAllUsers()){
			System.out.println(user);
		}
	}
}