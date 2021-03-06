package ru.skysoftlab.skylibs.security;

import java.io.Serializable;

import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import com.vaadin.cdi.access.JaasAccessControl;

@Singleton
@Alternative
public class Authenticator extends JaasAccessControl implements Serializable {

	private static final long serialVersionUID = -8775780572051229559L;

	public Authenticator() {
		String path = this.getClass().getResource("/login.config").getPath();
		System.setProperty("java.security.auth.login.config", path);
	}

}
