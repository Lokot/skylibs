package ru.skysoftlab.swt.viewer;

import org.eclipse.jface.viewers.ViewerFilter;

public abstract class AbstractViewerFilter extends ViewerFilter {

	@SuppressWarnings("unused")
	private String searchString;

	public void setSearchString(String searchString) {
		this.searchString = ".*" + searchString.toLowerCase() + ".*";
	}

	protected abstract boolean selectItem(Object s);
}
