package ru.skysoftlab.swt.control.adapters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

import ru.skysoftlab.swt.IControlItem;

public abstract class AbstractListAdapter extends AbstractControlAdapter<List> {

	protected Map<Integer, IControlItem> items = new HashMap<>();

	@Override
	public List createControl(Composite parent) {
		control = new List(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		addValues(getItems());
		return control;
	}

	@Override
	public void setControlValue(Object valObject) {
		for (Integer key : items.keySet()) {
			if (valObject instanceof Collection) {
				@SuppressWarnings("unchecked")
				Collection<IControlItem> t = (Collection<IControlItem>) valObject;
				for (IControlItem item : t) {
					if (items.get(key).equals(item)) {
						control.select(key);
					}
				}
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object getControlValue() {
		ArrayList rv = new ArrayList();
		for (int i : control.getSelectionIndices()) {
			IControlItem item = items.get(i);
			rv.add(item);
		}
		return rv;
	}

	@Override
	public void setEditable(boolean editable) {
		// Not used
	}

	public abstract IControlItem[] getItems();

	protected void addValues(IControlItem[] args) {
		int i = 0;
		items.clear();
		for (IControlItem object : args) {
			items.put(i, object);
			control.add(object.getName(), i);
			i++;
		}
	}
}
