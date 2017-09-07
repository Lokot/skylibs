package ru.skysoftlab.skylibs.security;

import static ru.skysoftlab.skylibs.security.Roles.ADMIN;
import static ru.skysoftlab.skylibs.security.Roles.GUEST;
import static ru.skysoftlab.skylibs.security.Roles.USER;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.enterprise.inject.Alternative;
import javax.security.auth.login.FailedLoginException;

import org.apache.openejb.core.security.jaas.LoginProvider;

@Alternative
public class PropertiesLoginProvider implements LoginProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.openejb.core.security.jaas.LoginProvider#authenticate(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public List<String> authenticate(String user, String password) throws FailedLoginException {
		Properties prop = new Properties();
		String filename = "users.properties";
		InputStream input = getClass().getClassLoader().getResourceAsStream(filename);
		if (input == null) {
			throw new FailedLoginException("Sorry, unable to find " + filename);
		}
		try {
			// load a properties file from class path, inside static method
			prop.load(input);
			String userPass = prop.getProperty(user);
			if (userPass != null && userPass.equals(password)) {
				if ("admin".equals(user)) {
					return ADMIN.getRoles();
				} else if ("user".equals(user)) {
					return USER.getRoles();
				}
			} else {
				if ("guest".equals(user)) {
					return GUEST.getRoles();
				}
			}
		} catch (IOException ex) {
			throw new FailedLoginException("Unable to load data from file " + filename);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		throw new FailedLoginException();
	}
}
