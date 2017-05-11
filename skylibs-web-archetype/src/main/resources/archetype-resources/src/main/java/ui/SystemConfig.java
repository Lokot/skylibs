#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ui;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.dto.SystemConfigDto;
import ${package}.impl.DataBaseProvider;
import ${package}.web.MainMenu;
import ru.skysoftlab.skylibs.events.SystemConfigEvent;
import ru.skysoftlab.skylibs.security.RolesList;
import ru.skysoftlab.skylibs.web.annatations.MainMenuItem;
import ru.skysoftlab.skylibs.web.ui.BaseMenuView;

import com.vaadin.cdi.CDIView;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Системные настройки.
 * 
 * @author Артём
 *
 */
@CDIView(MainMenu.SYSTEM)
@MainMenuItem(name = "Настройки", order = MainMenu.CONFIG, hasChilds = false)
@RolesAllowed({ RolesList.ADMIN })
public class SystemConfig extends BaseMenuView implements Button.ClickListener,
		ValueChangeListener {

	private static final long serialVersionUID = 2039928987238266962L;

	private Logger LOG = LoggerFactory.getLogger(SystemConfig.class);

	@Inject
	private DataBaseProvider dataBaseProvider;

	@Inject
	private javax.enterprise.event.Event<SystemConfigEvent> systemEvent;

	private BeanFieldGroup<SystemConfigDto> formFieldBindings;

	@Inject
	@RequestScoped
	private SystemConfigDto dto;

	private TextField simpleProp = new TextField("Простое свойство:");

	private Button save = new Button("Сохранить", this);

	@Override
	protected void configureComponents() {

	}

	@Override
	protected void buildLayout() {
		Label title = new Label("Системные настройки");
		FormLayout form = new FormLayout(simpleProp, save);
		formFieldBindings = BeanFieldGroup.bindFieldsBuffered(dto, this);
		VerticalLayout left = new VerticalLayout(title, form);
		left.setExpandRatio(form, 1);
		left.setSizeFull();
		left.setHeight("600px");
		HorizontalLayout mainLayout = new HorizontalLayout(left);
		mainLayout.setSizeFull();
		mainLayout.setExpandRatio(left, 1);

		layout.addComponent(mainLayout);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		try {
			formFieldBindings.commit();
			// Save DAO to backend with direct synchronous service API
			try {
				dataBaseProvider.saveConfig(dto);
				// событие изменения настроек
				systemEvent.fire(new SystemConfigEvent(dto.getDataForEvent()));
				Notification.show("Настройки сохранены.",
						Type.TRAY_NOTIFICATION);
			} catch (Exception e) {
				String msg = "System configuration save error";
				LOG.error(msg, e);
				Notification.show(msg, Type.ERROR_MESSAGE);
			}
		} catch (FieldGroup.CommitException e) {
			// Validation exceptions could be shown here
		}
	}

	@Override
	public void valueChange(ValueChangeEvent event) {

	}

}
