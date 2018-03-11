package ru.skysoftlab.swt.viewers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import ru.skysoftlab.swt.viewers.annotations.TableColumn;

/**
 * Провайдер вызывает метод соответствующий аннотации.
 * 
 * @author Lokot
 *
 */
public class AnnotationLabelProvider extends ColumnLabelProvider {

	/** Номер колонки. */
	private int columnIndex;

	public AnnotationLabelProvider(int columnIndex) {
		super();
		this.columnIndex = columnIndex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		Class<?> clazz = element.getClass();
		for (Method method : clazz.getMethods()) {
			if (method.isAnnotationPresent(TableColumn.class)) {
				TableColumn ann = method.getAnnotation(TableColumn.class);
				if (ann.index() == columnIndex) {
					try {
						return method.invoke(element).toString();
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						return "Not read property.";
					}
				}
			}
		}
		return "";
	}

}
