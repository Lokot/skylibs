package ru.skysoftlab.skylibs.web.navigation;

import java.io.Serializable;

import javax.enterprise.event.Observes;

public interface NavigationService extends Serializable {

	public static final String MAIN = "";
	public static final String LOGIN = "login";

	public void onNavigationEvent(@Observes NavigationEvent event);
}
