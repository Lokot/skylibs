package ru.skysoftlab.swt.control.adapters;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class TextAdapter extends AbstractControlAdapter<Text> {

	@Override
	public Text createControl(Composite parent) {
		control = new Text(parent, SWT.BORDER);
		return control;
	}

	@Override
	public void setControlValue(Object valObject) {
		control.setText(valObject.toString());
	}

	@Override
	public Object getControlValue() {
		return control.getText();
	}

	@Override
	public void setEditable(boolean editable) {
		control.setEditable(editable);
	}

}
