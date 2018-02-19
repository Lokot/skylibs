package ru.skysoftlab.swt.viewer;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import ru.skysoftlab.skylibs.common.hierarchy.IsParent;
import ru.skysoftlab.skylibs.common.hierarchy.IsChild;

public class ParentChildTreeContentProvider implements ITreeContentProvider {

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

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IsParent) {
			IsParent<?> parent = (IsParent<?>) parentElement;
			return parent.getChilds();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof IsChild) {
			IsChild<?> child = (IsChild<?>) element;
			return child.getParent();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof IsParent) {
			IsParent<?> parent = (IsParent<?>) element;
			return parent.hasChildren();
		}
		return false;
	}

}
