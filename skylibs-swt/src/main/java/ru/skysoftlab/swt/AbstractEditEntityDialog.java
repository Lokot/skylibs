package ru.skysoftlab.swt;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import ru.skysoftlab.skylibs.common.EditableEntity;

public abstract class AbstractEditEntityDialog<T extends EditableEntity<?>> extends AbstractEditDialog implements
		EditEntityDialog<T>{

	protected T entity;

	public AbstractEditEntityDialog(Shell parentShell) {
		super(parentShell);
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
}
