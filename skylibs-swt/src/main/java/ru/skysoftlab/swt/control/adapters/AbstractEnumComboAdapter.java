package ru.skysoftlab.swt.control.adapters;

import ru.skysoftlab.swt.IControlItem;

public abstract class AbstractEnumComboAdapter<T extends Enum<? extends IControlItem>> extends AbstractComboAdapter {

	protected Class<T> clazz;

	public AbstractEnumComboAdapter(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public IControlItem[] getItems() {
		return (IControlItem[]) clazz.getEnumConstants();
	}

}
