package com.packt.spring.aop.type;
public enum Role {
	ADMIN("ADM"), WRITER("WRT"), GUEST("GST"),USER("USR"),READER("RDR");
	private String name;
	private Role(String name) {
		this.name = name;
	}
	public static Role getRoleByName(String name) {
		for (Role role : Role.values()) {
			if (role.name.equals(name)) {
				return role;
			}
		}
		throw new IllegalArgumentException("NO ROLES [" + name + "]");
	}
	public String getName() {
		return this.name;
	}
}
