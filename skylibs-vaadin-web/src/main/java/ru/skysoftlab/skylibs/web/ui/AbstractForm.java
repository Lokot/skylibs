package ru.skysoftlab.skylibs.web.ui;

import static ru.skysoftlab.skylibs.common.EditableEntityState.NEW;
import static ru.skysoftlab.skylibs.common.EditableEntityState.UPDATE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

import ru.skysoftlab.skylibs.common.EditableEntity;
import ru.skysoftlab.skylibs.common.EditableEntityState;
import ru.skysoftlab.skylibs.events.EntityChangeEvent;

/**
 * Форма редактирования сущности.
 * 
 * @author Артём
 *
 * @param <T>
 *            класс сущности
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractForm<T extends EditableEntity> extends FormLayout {

	private static final long serialVersionUID = -2748949323565272299L;

	@Inject
	private javax.enterprise.event.Event<EntityChangeEvent> entityChangeEvent;

	/** Сущность. */
	protected T entity;
	protected EditableEntityState entityState;
	// Easily bind forms to beans and manage validation and buffering
	protected BeanFieldGroup<T> formFieldBindings;
	protected AbstractGridView<T, ? extends AbstractForm<T>> gridView;

	protected Button save = new Button("Сохранить", getSaveClickListener());
	protected Button cancel = new Button("Отмена", getCancelClickListener());

	@PostConstruct
	public void initForm() {
		configureComponents();
		buildLayout();
	}

	protected void configureComponents() {
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		setVisible(false);
	}

	private void buildLayout() {
		setSizeUndefined();
		setMargin(true);

		HorizontalLayout actions = new HorizontalLayout(save, cancel);
		actions.setSpacing(true);

		List<Component> components = new ArrayList<>();
		components.add(actions);
		components.addAll(getInputs());
		addComponents(components.toArray(new Component[components.size()]));
	}

	public void edit(T object) {
		this.entity = object;
		if (object != null) {
			entityState = UPDATE;
			// Bind the properties of the contact POJO to fiels in this form
			formFieldBindings = BeanFieldGroup.bindFieldsBuffered(object, this);
			setFocus();
		} else {
			entityState = NEW;
		}
		setVisible(object != null);
	}

	public void setGridView(AbstractGridView<T, ? extends AbstractForm<T>> gridView) {
		this.gridView = gridView;
	}

	protected Button.ClickListener getSaveClickListener() {
		return new Button.ClickListener() {

			private static final long serialVersionUID = 4489078023605774389L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					// Commit the fields from UI to DAO
					formFieldBindings.commit();
					// Save DAO to backend with direct synchronous service API
					gridView.getJpaContainer().addEntity(entity);
					// событие изменения настроек
					entityChangeEvent.fire(new EntityChangeEvent(entity.getId(), entity.getClass(), entityState));
					String msg = String.format("Сохранено '%s'.", entity.toString());
					Notification.show(msg, Type.TRAY_NOTIFICATION);
					gridView.refreshData();
				} catch (FieldGroup.CommitException e) {
					// Validation exceptions could be shown here
				} catch (javax.persistence.PersistenceException e) {
					String msg = String.format("Ошибка сохранения '%s'.", entity.toString());
					Notification.show(msg, Type.ERROR_MESSAGE);
				}
			}
		};
	}

	protected Button.ClickListener getCancelClickListener() {
		return new Button.ClickListener() {

			private static final long serialVersionUID = -5244857856527344654L;

			@Override
			public void buttonClick(ClickEvent event) {
				// Place to call business logic.
				Notification.show("Cancelled", Type.TRAY_NOTIFICATION);
				gridView.getGrid().select(null);
				setVisible(false);
			}
		};
	}

	protected abstract Collection<? extends Component> getInputs();

	protected abstract void setFocus();
}
