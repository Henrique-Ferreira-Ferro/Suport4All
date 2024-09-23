package com.io.Suport4All.enums;

public enum UserRole {
	
	ADMIN("admin"),
	USER("Usuario");
	
	private String name;
	
	UserRole(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	
}
