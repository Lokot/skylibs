package ru.skysoftlab.swt;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public interface AppChildContent {
	
	public Control createContents(final Composite parent);
	
}
