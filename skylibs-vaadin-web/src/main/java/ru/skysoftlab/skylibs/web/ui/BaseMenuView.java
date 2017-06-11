package ru.skysoftlab.skylibs.web.ui;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

/**
 * Базовый класс для всех страниц для гавного меню.
 * 
 * @author Loktionov Artem
 *
 */
public abstract class BaseMenuView extends CustomComponent implements View {

	private static final long serialVersionUID = 6006817026658320555L;

	protected final VerticalLayout layout = new VerticalLayout();

	@Inject
	private MenuBar barmenu;

	@PostConstruct
	public void initBaseMenuView() {
		// setSizeFull();
		setCompositionRoot(layout);
		layout.setMargin(true);
		layout.addComponent(barmenu);
		// layout.setSizeFull();
		// layout.setStyleName(Reindeer.LAYOUT_BLACK);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		getUI().setPollInterval(-1);
		configureComponents();
		layout.removeAllComponents();
		layout.addComponent(barmenu);
		buildLayout();
	}

	/**
	 * 
	 */
	protected abstract void configureComponents();
	
	/**
	 * 
	 */
	protected abstract void buildLayout();
}
