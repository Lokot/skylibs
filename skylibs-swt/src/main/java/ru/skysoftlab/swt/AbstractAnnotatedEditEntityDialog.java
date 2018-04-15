package ru.skysoftlab.swt;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import ru.skysoftlab.skylibs.common.EditableEntity;

public abstract class AbstractAnnotatedEditEntityDialog<T extends EditableEntity<?>>
		extends AbstractEditEntityDialog<T> {

	protected Map<String, Control> controls = new HashMap<>();
	protected Map<Control, ControlDecoration> decorations = new HashMap<>();
	private Class<T> entityClass;
	private FieldDecoration errorFieldDecoration = FieldDecorationRegistry.getDefault()
			.getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
	protected Map<String, Map<Integer, IControlItem>> listControlsArgs = new HashMap<>();

	public AbstractAnnotatedEditEntityDialog(Class<T> aEntityClass, Shell parentShell) {
		super(parentShell);
		entityClass = aEntityClass;
	}

	private Combo createCombo(Composite parent, Map<Integer, IControlItem> args) {
		Combo rv = new Combo(parent, 8);
		for (Entry<Integer, IControlItem> entry : args.entrySet()) {
			rv.add(entry.getValue().getName(), entry.getKey());
		}
		return rv;
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
		for (Method method : entityClass.getMethods()) {
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

			if (ann.control().equals(Combo.class)) {
				if (method.getReturnType().isEnum()) {
					Map<Integer, IControlItem> args = new HashMap<>();
					int i = 0;
					int selectedItemIndex = -1;
					Object propValue = null;
					if (entity != null) {
						try {
							propValue = method.invoke(entity);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					for (Object enumObj : method.getReturnType().getEnumConstants()) {
						args.put(i, (IControlItem) enumObj);
						if (entity != null && propValue != null && propValue.equals(enumObj)) {
							selectedItemIndex = i;
						}
						i++;
					}
					Combo control = createCombo(parent, args);
					String propName = getPropName(method);
					controls.put(propName, control);
					ControlDecoration dec = new ControlDecoration(control, SWT.RIGHT | SWT.TOP, control.getParent());
					decorations.put(control, dec);
					listControlsArgs.put(propName, args);
					if (selectedItemIndex >= 0) {
						control.select(selectedItemIndex);
					}
				} else {
					// TODO подумать
					throw new RuntimeException("Не верный тип данных для комбобокса");
				}
			} else {
				Text control = new Text(parent, SWT.BORDER);
				controls.put(getPropName(method), control);
				ControlDecoration dec = new ControlDecoration(control, SWT.RIGHT | SWT.TOP, control.getParent());
				decorations.put(control, dec);
				control.setLayoutData(data);
				control.setEditable(ann.editable());
				if (entity != null) {
					try {
						control.setText(method.invoke(entity).toString());
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
		return parent;
	}

	public void createNewEntity() {
		try {
			entity = entityClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		for (Method method : entityClass.getMethods()) {
			if (method.getName().contains("set")) {
				String propName = getPropName(method);
				Control control = controls.get(propName);
				if (control != null) {
					try {
						Object value = null;
						if (control instanceof Combo) {
							Combo combo = (Combo) control;
							value = listControlsArgs.get(propName).get(combo.getSelectionIndex());
							method.invoke(entity, value);
						} else {
							value = ((Text) control).getText();
							method.invoke(entity, value);
						}
						Set<ConstraintViolation<T>> violations = validator.validateValue(entityClass, propName, value);
						if (!violations.isEmpty()) {
							ControlDecoration dec = decorations.get(control);
							dec.setImage(errorFieldDecoration.getImage());
							dec.setDescriptionText(violations.iterator().next().getMessage());
							dec.show();
						} else {
							ControlDecoration dec = decorations.get(control);
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
