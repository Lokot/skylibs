package ru.skysoftlab.swt.viewers;

import org.eclipse.jface.viewers.ITreeContentProvider;

import ru.skysoftlab.skylibs.common.hierarchy.IsChild;
import ru.skysoftlab.skylibs.common.hierarchy.IsParent;

public class ParentChildTreeContentProvider extends StructuredContentProvider implements ITreeContentProvider {

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
