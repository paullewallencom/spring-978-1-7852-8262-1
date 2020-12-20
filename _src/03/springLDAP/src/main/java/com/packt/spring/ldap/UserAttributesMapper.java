package com.packt.spring.ldap;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

public class UserAttributesMapper implements AttributesMapper {

	public LDAPUser mapFromAttributes(Attributes attributes) throws NamingException {
		
		LDAPUser userObject = new LDAPUser();
		
		String commonName = (String)attributes.get("cn").get();
		userObject.setCommonName(commonName);
		if (attributes.get("telephoneNumber") == null){
			System.out.println("Telephone is null for " + commonName);
		}else{
			String telephone = attributes.get("telephoneNumber").get().toString();
			userObject.setTelephone(telephone);			
		}
		return userObject;
	}

}
