package ru.skysoftlab.swt.viewers;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class StructuredContentProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof IDataModel)
			return ((IDataModel) inputElement).getData().toArray();
		return null;
	}

	@Override
	public void inputChanged(Viewer v, Object oldInput, Object newInput) { }

	@Override
	public void dispose() { }

}
