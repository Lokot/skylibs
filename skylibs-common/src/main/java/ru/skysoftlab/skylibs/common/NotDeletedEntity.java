package ru.skysoftlab.skylibs.common;

import java.io.Serializable;

public interface NotDeletedEntity<K extends Serializable> extends EditableEntity<K> {
	
	public void hide();
	
	public boolean isDeleted();

}
