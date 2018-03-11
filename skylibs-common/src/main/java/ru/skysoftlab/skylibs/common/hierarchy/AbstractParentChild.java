package ru.skysoftlab.skylibs.common.hierarchy;

import java.util.Collection;

public class AbstractParentChild<COL extends Collection<C>, C extends IsChild<?>, P extends IsParent<?>>
		extends AbstractParent<COL, C> implements IsChild<P> {

	protected P parent;

	public AbstractParentChild(COL childs) {
		super(childs);
	}

	@Override
	public P getParent() {
		return parent;
	}

}
