package ru.skysoftlab.skylibs.web.ui.forms;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;

import javax.enterprise.inject.Default;

import ru.skysoftlab.skylibs.security.HashUtil;
import ru.skysoftlab.skylibs.security.Roles;
import ru.skysoftlab.skylibs.security.entitys.UserEntity;
import ru.skysoftlab.skylibs.web.ui.AbstractForm;

import com.vaadin.data.Validator;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;

/**
 * Форма устройств.
 * 
 * @author Loktionov Artem
 *
 */
@Default
public class UserForm extends AbstractForm<UserEntity> {

	private static final long serialVersionUID = 2372643403143137631L;

	private TextField name;
	private ComboBox role;
	private TextField pass1;
	private TextField pass2;

	@Override
	protected Collection<? extends Component> getInputs() {
		name = new TextField("Имя пользователя");
		pass1 = new TextField("Пароль");
		pass1.addValidator(new Validator() {

			private static final long serialVersionUID = -6379090040805784482L;

			@Override
			public void validate(Object value) throws InvalidValueException {
				if (pass1.getValue() == null || pass1.getValue().length() < 6) {
					throw new InvalidValueException("Пароль не задан");
				}
			}
		});
		pass2 = new TextField("Подтверждение пароля");
		pass2.addValidator(new Validator() {

			private static final long serialVersionUID = -3670054451052786950L;

			@Override
			public void validate(Object value) throws InvalidValueException {
				if (pass2.getValue() == null || pass2.getValue().length() < 6) {
					throw new InvalidValueException("Подтверждение пароля не задано");
				}
				if (!pass2.getValue().equals(pass1.getValue())) {
					throw new InvalidValueException("Пароли не заданы");
				}
			}
		});
		role = new ComboBox("Роль", EnumSet.allOf(Roles.class));
		Collection<Component> rv = new ArrayList<>();
		rv.add(name);
		rv.add(role);
		rv.add(pass1);
		rv.add(pass2);
		return rv;
	}

	@Override
	protected void setFocus() {
		name.focus();
	}

	@Override
	protected Button.ClickListener getSaveClickListener() {
		return new Button.ClickListener() {

			private static final long serialVersionUID = 4489078023605774389L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					// Commit the fields from UI to DAO
					formFieldBindings.commit();
					String hashPass = HashUtil.md5DigestForString(pass1.getValue());
					entity.setPassHash(hashPass);
					// Save DAO to backend with direct synchronous service API
					gridView.getJpaContainer().addEntity(entity);
					String msg = String.format("Saved '%s'.", entity.toString());
					Notification.show(msg, Type.TRAY_NOTIFICATION);
				} catch (FieldGroup.CommitException e) {
					// Validation exceptions could be shown here
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
		};
	}

}
