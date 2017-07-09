package ru.skysoftlab.skylibs.security;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.security.auth.login.FailedLoginException;

import org.apache.openejb.core.security.jaas.LoginProvider;

@Default
public class SimpleLoginProvider implements LoginProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.openejb.core.security.jaas.LoginProvider#authenticate(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public List<String> authenticate(String user, String password) throws FailedLoginException {
		if ("admin".equals(user) && "admin".equals(password)) {
			return Arrays.asList("ADMIN");
		}
		if ("user".equals(user) && "user".equals(password)) {
			return Arrays.asList("USER");
		}
		throw new FailedLoginException();
	}

}
