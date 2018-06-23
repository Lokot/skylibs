package ru.skysoftlab.swt.control.adapters;

import org.eclipse.swt.widgets.Control;

import ru.skysoftlab.swt.control.IControlAdapter;

public abstract class AbstractControlAdapter<T extends Control> implements IControlAdapter<T>{

	protected T control;
	
	@Override
	public T getControl() {
		return control;
	}
}
