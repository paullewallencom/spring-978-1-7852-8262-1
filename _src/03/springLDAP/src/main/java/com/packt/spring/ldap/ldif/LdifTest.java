package com.packt.spring.ldap.ldif;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.springframework.core.io.FileSystemResource;
import org.springframework.ldap.ldif.parser.LdifParser;
import org.springframework.ldap.ldif.parser.Parser;

public class LdifTest {

	public static void main(String[] args) throws Exception{
		
		Parser parser = new LdifParser();
				
		parser.setResource(new FileSystemResource("users.ldif"));
		parser.open();
		
		while (parser.hasMoreRecords()){
			
			Attributes attributes = parser.getRecord();
			String personDetails = getPersonDetails(attributes);
			System.out.println(personDetails);
		}
	}

	private static String getPersonDetails(Attributes attributes) throws Exception{

		StringBuilder personDetails = new StringBuilder();
		personDetails.append("{");	
		
		NamingEnumeration<? extends Attribute> attributeNames = attributes.getAll();
		while (attributeNames.hasMoreElements()) {
			
			Attribute attribute = attributeNames.next();
			personDetails.append("[" + attribute.getID());
			
			@SuppressWarnings("unchecked")
			NamingEnumeration<String> attributeValues = (NamingEnumeration<String>) attribute.getAll();
			while (attributeValues.hasMoreElements()){
				
				String attributeValue = attributeValues.next();
				personDetails.append("(").append(attributeValue).append(")");
			}
			personDetails.append("]");
		}
		personDetails.append("}");
		return personDetails.toString();
	}
}
