package ru.skysoftlab.swt.viewer;

import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

public abstract class AbstractViewerComparator extends ViewerComparator {

	private int propertyIndex;
	private static final int DESCENDING = 0;
	private int direction = 1;

	public AbstractViewerComparator() {
		this.propertyIndex = 0;
		// direction = DESCENDING;
	}

	public int getDirection() {
		return direction == 1 ? SWT.DOWN : SWT.UP;
	}

	public void setColumn(int column) {
		if (column == this.propertyIndex) {
			direction = 1 - direction;
		} else {
			this.propertyIndex = column;
			direction = DESCENDING;
		}
	}

}
