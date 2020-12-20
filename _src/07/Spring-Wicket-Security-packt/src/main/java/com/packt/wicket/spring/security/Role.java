package com.packt.wicket.spring.security;
public enum Role {
	 PUBLISHER("publish"),AUTHOR("author"),READER("read");
    private final String springSecurityRoleName;
	private Role(String springSecurityRoleName) {
		this.springSecurityRoleName = springSecurityRoleName;
	}
	public String getSpringSecurityRoleName() {
		return springSecurityRoleName;
	}
}