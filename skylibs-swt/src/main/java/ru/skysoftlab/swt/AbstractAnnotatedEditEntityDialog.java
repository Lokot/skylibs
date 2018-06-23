package ru.skysoftlab.swt;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import ru.skysoftlab.skylibs.common.EditableEntity;
import ru.skysoftlab.swt.control.IControlAdapter;

public abstract class AbstractAnnotatedEditEntityDialog<T extends EditableEntity<?>>
		extends AbstractEditEntityDialog<T> {

	private FieldDecoration errorFieldDecoration = FieldDecorationRegistry.getDefault()
			.getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
	
	protected Map<String, IControlAdapter<?>> controls = new HashMap<>();
	protected Map<IControlAdapter<?>, ControlDecoration> decorations = new HashMap<>();
	protected Map<String, Map<Integer, IControlItem>> listControlsArgs = new HashMap<>();

	public AbstractAnnotatedEditEntityDialog(Class<T> aEntityClass, Shell parentShell) {
		super(aEntityClass, parentShell);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse
	 * .swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.marginRight = 8; // Margin at right large enough for your decoration
		layout.numColumns = 2;
		parent.setLayout(layout);

		List<Method> annList = new ArrayList<>();
		for (Method method : getEntityClass().getMethods()) {
			if (method.isAnnotationPresent(ru.skysoftlab.swt.viewers.annotations.DialogProp.class)) {
				annList.add(method);
			}
		}
		annList.sort(new Comparator<Method>() {
			@Override
			public int compare(Method o1, Method o2) {
				ru.skysoftlab.swt.viewers.annotations.DialogProp ann1 = o1
						.getAnnotation(ru.skysoftlab.swt.viewers.annotations.DialogProp.class);
				ru.skysoftlab.swt.viewers.annotations.DialogProp ann2 = o2
						.getAnnotation(ru.skysoftlab.swt.viewers.annotations.DialogProp.class);
				return Integer.compare(ann1.index(), ann2.index());
			}
		});
		for (Method method : annList) {
			ru.skysoftlab.swt.viewers.annotations.DialogProp ann = method
					.getAnnotation(ru.skysoftlab.swt.viewers.annotations.DialogProp.class);

			Label label = new Label(parent, SWT.NONE);
			label.setText(ann.name());
			
			Class<? extends IControlAdapter<? extends Control>> adapterClass = ann.adapter();
			try {
				IControlAdapter<? extends Control> adapter = adapterClass.newInstance();
				adapter.createControl(parent);
				controls.put(getPropName(method), adapter);
				ControlDecoration dec = new ControlDecoration(adapter.getControl(), SWT.RIGHT | SWT.TOP, adapter.getControl().getParent());
				decorations.put(adapter, dec);
				adapter.getControl().setLayoutData(data);
				adapter.setEditable(ann.editable());
				if (entity != null) {
					try {
						adapter.setControlValue(method.invoke(entity));
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (InstantiationException | IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return parent;
	}

	private String getPropName(Method method) {
		return method.getName().replaceFirst("get", "").replaceFirst("set", "").toLowerCase();
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		if (entity == null) {
			createNewEntity();
		}
		for (Method method : getEntityClass().getMethods()) {
			if (method.getName().contains("set")) {
				String propName = getPropName(method);
				IControlAdapter<?> adapter = controls.get(propName);
				if (adapter != null) {
					try {
						Object value = adapter.getControlValue();
						Set<ConstraintViolation<T>> violations = validator.validateValue(getEntityClass(), propName, value);
						if (!violations.isEmpty()) {
							ControlDecoration dec = decorations.get(adapter);
							dec.setImage(errorFieldDecoration.getImage());
							dec.setDescriptionText(violations.iterator().next().getMessage());
							dec.show();
						} else {
							method.invoke(entity, value);
							ControlDecoration dec = decorations.get(adapter);
							dec.hide();
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		// TODO оптимизировать
		Set<ConstraintViolation<T>> violations = validator.validate(entity);
		if (violations.isEmpty()) {
			commitEntity();
			close();
		} else {
			String message = "";
			for (ConstraintViolation<T> violation : violations) {
				message += violation.getMessage() + "\n";
			}
			setErrorMessage(message);
		}
	}
	
}
