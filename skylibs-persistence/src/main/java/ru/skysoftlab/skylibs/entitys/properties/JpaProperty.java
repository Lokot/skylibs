package ru.skysoftlab.skylibs.entitys.properties;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import ru.skysoftlab.skylibs.common.EditableEntity;
import ru.skysoftlab.skylibs.properties.IDbProperty;

@Entity
public class JpaProperty implements IDbProperty, EditableEntity<String>, Serializable {

	private static final long serialVersionUID = -1284259336857932785L;

	@Id
	private String id;
	private String propertyType;
	private String value;

	@Override
	public String getId() {
		return id;
	}

	public String getPropertyType() {
		return propertyType;
	}

	@Override
	@Transient
	public Class<?> getType() {
		try {
			return Class.forName(propertyType);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	@Override
	public String getValue() {
		return value;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
