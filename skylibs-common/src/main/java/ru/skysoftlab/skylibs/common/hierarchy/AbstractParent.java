package ru.skysoftlab.skylibs.common.hierarchy;

import java.util.Collection;

public class AbstractParent<C extends Collection<T>, T extends IsChild<?>> implements IsParent<T> {

	protected C childs;

	public AbstractParent(C childs) {
		this.childs = childs;
	}

	@Override
	public boolean hasChildren() {
		if (this.childs == null)
			return false;
		return this.childs.size() > 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] getChilds() {
		return (T[]) this.childs.toArray(new IsChild[childs.size()]);
	}

}
