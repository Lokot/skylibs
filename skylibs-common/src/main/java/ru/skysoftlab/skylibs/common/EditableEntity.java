package ru.skysoftlab.skylibs.common;

import java.io.Serializable;

public interface EditableEntity<K extends Serializable> extends Serializable {
	
	/**
	 * Возвращает ключ сущности.
	 * 
	 * @return
	 */
	public K getId();
}
