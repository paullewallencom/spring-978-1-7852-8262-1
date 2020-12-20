package com.packt.spring.ldap.odm;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

@Entry(objectClasses = {"applicationEntity", "top"})
public class ApplicationEntity {

	@Id
	private Name distinguisedName;
	
	@Attribute(name="cn")
	private String cn;
	
	@Attribute(name="description")
	private String description;

	@Attribute(name="presentationAddress")
	private String presentationAddress;

	@Attribute(name="objectClass")
	private List<String> objectClassNames;
	
	public ApplicationEntity(){
		objectClassNames = new ArrayList<String>();
	}
	
	public Name getDistinguisedName() {
		return distinguisedName;
	}

	public void setDistinguisedName(Name distinguisedName) {
		this.distinguisedName = distinguisedName;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPresentationAddress() {
		return presentationAddress;
	}

	public void setPresentationAddress(String presentationAddress) {
		this.presentationAddress = presentationAddress;
	}

	public List<String> getObjectClassNames() {
		return objectClassNames;
	}

	public void setObjectClassNames(List<String> objectClassNames) {
		this.objectClassNames = objectClassNames;
	}		

	public String toString(){
		return distinguisedName + "#" + cn + "#" + description + "#" + objectClassNames + "#" + presentationAddress;
	}
}
