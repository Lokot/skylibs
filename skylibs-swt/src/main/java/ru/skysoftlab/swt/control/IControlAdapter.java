package ru.skysoftlab.swt.control;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public interface IControlAdapter<T extends Control> {
	
	public T createControl(Composite parent);
	
	public T getControl();

	public void setControlValue(Object valObject);
	
	public Object getControlValue();
	
	public void setEditable(boolean editable);
}
