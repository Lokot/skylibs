package ru.skysoftlab.swt.control.adapters;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import ru.skysoftlab.swt.IControlItem;

public abstract class AbstractComboAdapter extends AbstractControlAdapter<Combo> {

	protected Map<Integer, IControlItem> items = new HashMap<>();
	
	@Override
	public Combo createControl(Composite parent) {
		control = new Combo(parent, 8);
		addValues(getItems());
		return control;
	}
	
	@Override
	public void setControlValue(Object valObject) {
		for (Integer key : items.keySet()) {
			if (items.get(key).equals(valObject)) {
				control.select(key);
				return;
			}
		}
	}

	@Override
	public Object getControlValue() {
		return items.get(control.getSelectionIndex());
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
