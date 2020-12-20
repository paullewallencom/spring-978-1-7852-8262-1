package com.packt.spring.ldap.operations;
import java.util.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ldap.core.LdapTemplate;
import com.packt.spring.ldap.*;
public class SimpleSearch {
	private LdapTemplate ldapTemplate;
		@SuppressWarnings("unchecked")
	public Set<LDAPUser> getAllUsers(){
		UserAttributesMapper mapper = new UserAttributesMapper();
		return new HashSet<LDAPUser>(ldapTemplate.search("ou=users,ou=system", "(objectClass=person)", mapper));
	}
	public void setLdapTemplate(LdapTemplate ldapTemplate){
		this.ldapTemplate = ldapTemplate;
	}
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("packtldap.xml");
		SimpleSearch simpleSearch = new SimpleSearch();
		simpleSearch.setLdapTemplate(context.getBean("ldapTemplate", LdapTemplate.class));
		for (LDAPUser user : simpleSearch.getAllUsers()){
			System.out.println(user); }}}