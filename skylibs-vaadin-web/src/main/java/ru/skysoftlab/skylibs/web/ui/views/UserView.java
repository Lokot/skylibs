package ru.skysoftlab.skylibs.web.ui.views;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;

import ru.skysoftlab.skylibs.security.RolesList;
import ru.skysoftlab.skylibs.security.entitys.UserEntity;
import ru.skysoftlab.skylibs.web.jpa.UserEntityProviderBean;
import ru.skysoftlab.skylibs.web.ui.AbstractGridView;
import ru.skysoftlab.skylibs.web.ui.forms.UserForm;

import com.vaadin.addon.jpacontainer.EntityProvider;
import com.vaadin.data.Container.Indexed;

/**
 * Управление устройствами.
 * 
 * @author Loktionov Artem
 *
 */
@RolesAllowed({ RolesList.ADMIN })
public class UserView extends AbstractGridView<UserEntity, UserForm> {

	private static final long serialVersionUID = 6698245813955647506L;

	@Inject
	private UserEntityProviderBean entityProvider;
	
	@Inject
	private UserForm form;

	public UserView() {
		super(UserEntity.class);
	}

	@Override
	protected String getNewButtonLabel() {
		return "Новый пользователь";
	}

	@Override
	protected Object[] getRemoveColumn() {
		return new String[] { "id", "passHash" };
	}

	@Override
	protected Object[] getColumnOrder() {
		return new String[] { "name", "role" };
	}

	@Override
	protected Indexed refreshData(String value) {
		return getJpaContainer();
	}

	@Override
	protected String getTitle() {
		return "Список пользователей";
	}

	@Override
	protected Map<String, String> getColumnsNames() {
		Map<String, String> rv = new HashMap<>();
		rv.put("name", "Имя пользователя");
		rv.put("role", "Роль");
		return rv;
	}

	@Override
	protected EntityProvider<UserEntity> getEntityProvider() {
		return entityProvider;
	}

	@Override
	protected UserForm getEntityForm() {
		return form;
	}

}
