package ru.skysoftlab.swt;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import ru.skysoftlab.skylibs.common.EditableEntity;

public abstract class AbstractEditDialog<T extends EditableEntity<?>> extends TitleAreaDialog implements
		EditEntityDialog<T>, SelectionListener {

	protected T entity;
	private GridData data = new GridData();

	public AbstractEditDialog(Shell parentShell) {
		super(parentShell);
		data.horizontalAlignment = SWT.FILL;
		data.grabExcessHorizontalSpace = true;
	}

	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		String title = "Добавить " + getEntityName();
		if (entity != null) {
			title = "Изменить " + getEntityName();
		}
		setTitle(title);
		setMessage(getEntityMessage(), IMessageProvider.INFORMATION);
		return contents;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		((GridLayout) parent.getLayout()).numColumns++;
		Button button = new Button(parent, SWT.PUSH);
		button.setText("Сохранить");
		button.setFont(JFaceResources.getDialogFont());
		button.addSelectionListener(this);
	}

	@Override
	public void editOpen(T department) {
		this.entity = department;
		super.open();
	}

	@Override
	public T getEntity() {
		return entity;
	}

	protected abstract String getEntityName();

	protected abstract String getEntityMessage();

	/**
	 * Sent when default selection occurs in the control. The default behavior
	 * is to do nothing.
	 *
	 * @param e
	 *            an event containing information about the default selection
	 */
	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	protected Text createText(Composite parent) {
		Text rv = new Text(parent, SWT.BORDER);
		rv.setLayoutData(data);
		return rv;
	}

}
