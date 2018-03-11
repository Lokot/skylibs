package ru.skysoftlab.swt.viewers;

import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

public abstract class AbstractViewerComparator extends ViewerComparator {

	protected int propertyIndex;
	protected static final int DESCENDING = 0;
	protected int direction = 1;

	public AbstractViewerComparator() {
		this.propertyIndex = 0;
		// direction = DESCENDING;
	}

	public int getDirection() {
		return direction == 1 ? SWT.DOWN : SWT.UP;
	}

	public void setColumn(int column) {
		// TODO удалить
		System.out.println(column);
		if (column == this.propertyIndex) {
			direction = 1 - direction;
		} else {
			this.propertyIndex = column;
			direction = DESCENDING;
		}
	}

}
