package ru.skysoftlab.skylibs.security;

import java.util.Arrays;
import java.util.List;

public enum Roles {
	
	GUEST(RolesList.GUEST), 
	USER(RolesList.GUEST, RolesList.USER), 
	ADMIN(RolesList.GUEST, RolesList.USER, RolesList.ADMIN);

	private String[] rolesNames;

	private Roles(String... rolesNames) {
		this.rolesNames = rolesNames;
	}

	public List<String> getRoles() {
		return Arrays.asList(this.rolesNames);
	}
}
