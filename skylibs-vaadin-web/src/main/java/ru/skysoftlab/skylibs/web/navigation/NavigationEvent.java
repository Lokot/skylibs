package ru.skysoftlab.skylibs.web.navigation;

/**
 * Событие навигации.
 * 
 * @author Локтионов А.Г.
 *
 */
public class NavigationEvent {

	private final String navigateTo;

	public NavigationEvent(String navigateTo) {
		this.navigateTo = navigateTo;
	}

	public String getNavigateTo() {
		return navigateTo;
	}

}
