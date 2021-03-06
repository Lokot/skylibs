package ru.skysoftlab.skylibs.web.navigation;

import javax.inject.Inject;

import ru.skysoftlab.skylibs.annatations.SimpleQualifier;

import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Theme("mytheme")
@CDIUI("")
@SimpleQualifier(MainVaadinUI.NAME)
public class MainVaadinUI extends UI {

	private static final long serialVersionUID = 455094064407351011L;

	public static final String NAME = "mainUi";

	@Inject
	private javax.enterprise.event.Event<NavigationEvent> navigationEvent;
	
	@Override
	protected void init(VaadinRequest request) {
		navigationEvent.fire(new NavigationEvent(NavigationService.LOGIN));
	}

}
