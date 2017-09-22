package ru.skysoftlab.skylibs.security;

import static ru.skysoftlab.skylibs.security.Roles.ADMIN;
import static ru.skysoftlab.skylibs.security.Roles.GUEST;
import static ru.skysoftlab.skylibs.security.Roles.USER;

import java.util.List;
import java.util.Properties;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.security.auth.login.FailedLoginException;

import org.apache.openejb.core.security.jaas.LoginProvider;

import ru.skysoftlab.skylibs.annatations.AppPropertyFile;

@Alternative 
public class PropertiesLoginProvider implements LoginProvider {

	@Inject
	@AppPropertyFile("users.properties")
	private Properties prop = new Properties();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.openejb.core.security.jaas.LoginProvider#authenticate(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public List<String> authenticate(String user, String password) throws FailedLoginException {
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
		throw new FailedLoginException();
	}
}
