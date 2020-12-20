package com.packt.spring.ldap.operations;

import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;

import com.packt.spring.ldap.LDAPUser;

public class ModifyUser {

	private LdapTemplate ldapTemplate;
	
	public void modify(String commonName, String telephone){
	
		String baseDn = "ou=users,ou=system";
		DistinguishedName distinguisedName = new DistinguishedName(baseDn);
		distinguisedName.add("cn", commonName);
					
		Attribute telephoneAttribute = new BasicAttribute("telephoneNumber", telephone);
		ModificationItem telephoneItem = new ModificationItem(
			DirContext.REPLACE_ATTRIBUTE, telephoneAttribute);
		ldapTemplate.modifyAttributes(distinguisedName, new ModificationItem[]{telephoneItem});
	}

	public void setLdapTemplate(LdapTemplate ldapTemplate){
		this.ldapTemplate = ldapTemplate;
	}
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("packtldap.xml");	
		LdapTemplate ldapTemplate = context.getBean("ldapTemplate", LdapTemplate.class);
		
		ModifyUser modifyPerson = new ModifyUser();
		modifyPerson.setLdapTemplate(ldapTemplate);
		modifyPerson.modify("Steve David", "9999999");
		
		SimpleSearch simpleSearch = new SimpleSearch();
		simpleSearch.setLdapTemplate(ldapTemplate);
		for (LDAPUser user : simpleSearch.getAllUsers()){
			System.out.println(user);
		}
	}
}