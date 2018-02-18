package ru.skysoftlab.skylibs.common.hierarchy;

public interface IsChild<T extends IsParent<?>> {

	public T getParent();
}
