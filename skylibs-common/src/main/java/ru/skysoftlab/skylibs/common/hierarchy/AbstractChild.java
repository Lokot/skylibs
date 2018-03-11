package ru.skysoftlab.skylibs.common.hierarchy;

public class AbstractChild<T extends IsParent<?>> implements IsChild<T>{

	protected T parent;
	
	@Override
	public T getParent() {
		return parent;
	}

}
