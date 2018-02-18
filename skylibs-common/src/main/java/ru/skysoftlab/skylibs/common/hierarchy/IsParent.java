package ru.skysoftlab.skylibs.common.hierarchy;

public interface IsParent<T extends IsChild<?>> {

	public boolean hasChildren();
	
	public T[] getChilds();
}
