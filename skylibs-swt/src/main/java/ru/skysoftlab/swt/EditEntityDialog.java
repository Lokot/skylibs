package ru.skysoftlab.swt;

import ru.skysoftlab.skylibs.common.EditableEntity;

public interface EditEntityDialog<T extends EditableEntity<?>> {

	public void editOpen(T department);

	public T getEntity();

}
