package ru.skysoftlab.skylibs.events;

import ru.skysoftlab.skylibs.common.EditableEntityState;

public class EntityChangeEvent {

	private Object id;
	private Class<?> entityClass;
	private EditableEntityState state;

	public EntityChangeEvent(Object id, Class<?> entityClass, EditableEntityState state) {
		super();
		this.id = id;
		this.entityClass = entityClass;
		this.state = state;
	}

	public EditableEntityState getState() {
		return state;
	}

	public void setState(EditableEntityState state) {
		this.state = state;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Class<?> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}

}
