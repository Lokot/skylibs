package ru.skysoftlab.skylibs.security;

import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.apache.openejb.core.security.jaas.GroupPrincipal;
import org.apache.openejb.core.security.jaas.LoginProvider;
import org.apache.openejb.core.security.jaas.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CdiLoginModule implements LoginModule {
	
	private Logger LOG = LoggerFactory.getLogger(CdiLoginModule.class);

	private Subject subject;
	private CallbackHandler callbackHandler;

	public Set<Principal> principals = new LinkedHashSet<Principal>();

	private UserData userData;

	@Inject
	private LoginProvider loginProvider;

	private final class UserData {
		public final String user;
		public final String pass;
		public final Set<String> groups = new HashSet<String>();

		private UserData(final String user, final String pass) {
			this.user = user;
			this.pass = pass;
		}
	}

	@Override
	public void initialize(final Subject subject, final CallbackHandler callbackHandler,
			final Map<String, ?> sharedState, final Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
	}

	private UserData getUserData() throws LoginException {
		final Callback[] callbacks = new Callback[2];

		callbacks[0] = new NameCallback("Username: ");
		callbacks[1] = new PasswordCallback("Password: ", false);
		try {
			this.callbackHandler.handle(callbacks);
		} catch (final IOException ioe) {
			throw new LoginException(ioe.getMessage());
		} catch (final UnsupportedCallbackException uce) {
			throw new LoginException(uce.getMessage() + " not available to obtain information from user");
		}

		final String user = ((NameCallback) callbacks[0]).getName();

		char[] tmpPassword = ((PasswordCallback) callbacks[1]).getPassword();
		if (tmpPassword == null) {
			tmpPassword = new char[0];
		}

		final String password = new String(tmpPassword);

		return new UserData(user, password);
	}

	@Override
	public boolean login() throws LoginException {
		if (loginProvider == null) {
			throw new FailedLoginException("No LoginProvider defined.");
		}

		this.userData = getUserData();

		final List<String> myGroups = loginProvider.authenticate(this.userData.user, this.userData.pass);
		if (myGroups != null) {
			this.userData.groups.addAll(myGroups);
		}

		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		this.principals.add(new UserPrincipal(this.userData.user));

		for (final String myGroup : this.userData.groups) {
			principals.add(new GroupPrincipal(myGroup));
		}

		this.subject.getPrincipals().addAll(this.principals);

		clear();

		LOG.debug("commit");
		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		clear();
		LOG.debug("abort");
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		this.subject.getPrincipals().removeAll(this.principals);
		this.principals.clear();

		LOG.debug("logout");
		return true;
	}

	private void clear() {
		this.userData = null;
	}

}
