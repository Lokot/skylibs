package ru.skysoftlab.swt.viewers;

import org.eclipse.jface.viewers.ViewerFilter;

public abstract class AbstractViewerFilter<T> extends ViewerFilter {

	protected String searchString;

	public void setSearchString(String searchString) {
		this.searchString = ".*" + searchString.toLowerCase() + ".*";
	}

	protected abstract boolean selectItem(T s);
}
