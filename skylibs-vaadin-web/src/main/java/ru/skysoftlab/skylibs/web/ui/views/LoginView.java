package ru.skysoftlab.skylibs.web.ui.views;

import javax.inject.Inject;
import javax.servlet.ServletException;

import com.vaadin.cdi.CDIView;
import com.vaadin.cdi.access.JaasAccessControl;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

import ru.skysoftlab.skylibs.web.common.AppNames;
import ru.skysoftlab.skylibs.web.navigation.NavigationEvent;
import ru.skysoftlab.skylibs.web.navigation.NavigationService;

/**
 * Страница аутентификации.
 * 
 * @author Артём
 *
 */
@CDIView(NavigationService.LOGIN)
public class LoginView extends CustomComponent implements View, Button.ClickListener {
	private static final long serialVersionUID = 7457479619765665383L;

	private Button guestButton = new Button("Гость", this);

	private Button loginButton = new Button("Вход", this);
	@Inject
	private javax.enterprise.event.Event<NavigationEvent> navigationEvent;
	private PasswordField password = new PasswordField("Пароль:");
	private TextField user = new TextField("Пользователь:");

	@Override
	public void buttonClick(ClickEvent event) {
		String username = user.getValue();
		String password = this.password.getValue();
		if (event.getButton().equals(guestButton)) {
			username = "guest";
		}
		try {
			JaasAccessControl.login(username, password);
			getSession().setAttribute("user", username);
			navigationEvent.fire(new NavigationEvent(NavigationService.MAIN));
		} catch (ServletException e) {
			Notification.show(e.getMessage(), Type.TRAY_NOTIFICATION);
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		getSession().setAttribute("user", null);

		setSizeFull();

		user.setWidth("300px");
		user.setRequired(true);
		user.setInvalidAllowed(false);

		password.setWidth("300px");
		password.setRequired(true);
		password.setValue("");
		password.setNullRepresentation("");

		HorizontalLayout buttonsLayout = new HorizontalLayout(loginButton, guestButton);

		Resource res = new ThemeResource("img/logo.png");
		Image image = new Image(null, res);
		image.setStyleName("appLogo");

		Label appName = new Label(System.getProperty(AppNames.APP_NAME, "App name"));
		appName.setStyleName("appNameLabel");
		appName.removeStyleName("v-widget");

		VerticalLayout logoLayout = new VerticalLayout(image, appName);
		logoLayout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
		logoLayout.setComponentAlignment(appName, Alignment.MIDDLE_CENTER);

		VerticalLayout fields = new VerticalLayout(user, password, buttonsLayout);
		fields.setSpacing(true);
		fields.setMargin(new MarginInfo(true, true, true, false));
		fields.setSizeUndefined();

		VerticalLayout viewLayout = new VerticalLayout(logoLayout, fields);
		viewLayout.setSizeFull();
		viewLayout.setComponentAlignment(logoLayout, Alignment.BOTTOM_CENTER);
		viewLayout.setComponentAlignment(fields, Alignment.TOP_CENTER);
		viewLayout.setStyleName(Reindeer.LAYOUT_BLACK);
		setCompositionRoot(viewLayout);
		user.focus();
	}
}