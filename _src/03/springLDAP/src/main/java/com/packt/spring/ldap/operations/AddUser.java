package com.packt.spring.ldap.operations;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;

import com.packt.spring.ldap.LDAPUser;
public class AddUser {
	private LdapTemplate ldapTemplate;
	public void add(String commonName, String surName, String telephone){
		String baseDn = "ou=users,ou=system";
		DistinguishedName distinguisedName = new DistinguishedName(baseDn);
		distinguisedName.add("cn", commonName);
		Attributes userAttributes = new BasicAttributes();
		userAttributes.put("sn", surName);
		userAttributes.put("telephoneNumber", telephone);
		BasicAttribute classAttribute = new BasicAttribute("objectclass");
		classAttribute.add("top");
		classAttribute.add("person");		
		userAttributes.put(classAttribute);
		ldapTemplate.bind(distinguisedName, null, userAttributes);
	}
	public void setLdapTemplate(LdapTemplate ldapTemplate){
		this.ldapTemplate = ldapTemplate;
	}
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("packtldap.xml");	
		LdapTemplate ldapTemplate = context.getBean("ldapTemplate", LdapTemplate.class);
		AddUser addPerson = new AddUser();
		addPerson.setLdapTemplate(ldapTemplate);
		addPerson.add("ABCDEF", "abc", "555");
		SimpleSearch simpleSearch = new SimpleSearch();
		simpleSearch.setLdapTemplate(ldapTemplate);
		for (LDAPUser user : simpleSearch.getAllUsers()){
			System.out.println(user);
		}
	}
}