package ru.skysoftlab.swt;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import ru.skysoftlab.skylibs.common.EditableEntity;

// TODO создание сущности из класса
public abstract class AbstractEditEntityDialog<T extends EditableEntity<?>> extends AbstractEditDialog
		implements EditEntityDialog<T> {

	private Class<T> entityClass;
	private T sucsessEntity;
	protected T entity;

	public AbstractEditEntityDialog(Class<T> aEntityClass, Shell parentShell) {
		super(parentShell);
		entityClass = aEntityClass;
	}
	
	public void createNewEntity() {
		try {
			entity = getEntityClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		String title = "Добавить " + getEntityName();
		if (entity != null) {
			title = "Изменить " + getEntityName();
		}
		setTitle(title);
		// TODO не отображается при повторном вызове
		setMessage(getEntityMessage(), IMessageProvider.INFORMATION);
		return contents;
	}

	@Override
	public void editOpen(T aEntity) {
		this.entity = aEntity;
		this.sucsessEntity = null;
		super.open();
	}

	@Override
	public T getEntity() {
		return sucsessEntity;
	}
	
	public void commitEntity() {
		sucsessEntity = entity;
	}

	protected abstract String getEntityMessage();

	protected abstract String getEntityName();

	public Class<T> getEntityClass() {
		return entityClass;
	}
	
}
