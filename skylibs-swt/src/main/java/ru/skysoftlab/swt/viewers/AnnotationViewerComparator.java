package ru.skysoftlab.swt.viewers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.jface.viewers.Viewer;

import ru.skysoftlab.swt.viewers.annotations.ClassCompare;
import ru.skysoftlab.swt.viewers.annotations.TableColumn;

public class AnnotationViewerComparator extends AbstractViewerComparator {

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int compare(Viewer viewer, Object e1, Object e2) {
		int rc = 0;
		if (e1.getClass().equals(e2.getClass())) {
			for (Method method : e1.getClass().getMethods()) {
				if (method.isAnnotationPresent(TableColumn.class)) {
					TableColumn ann = method.getAnnotation(TableColumn.class);
					if (ann.index() == propertyIndex) {
						Class<Comparable> cls = Comparable.class;
						if (cls.isAssignableFrom(method.getReturnType())) {
							try {
								Comparable c = (Comparable) method.invoke(e1);
								rc = c.compareTo((Comparable) method.invoke(e2));
							} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
								e.printStackTrace();
								rc = 0;
							}
						}
						break;
					}
				}
			}
		} else {
			Class<ClassCompare> annClass = ClassCompare.class;
			Class<?> class1 = e1.getClass();
			Class<?> class2 = e2.getClass();
			if (class1.isAnnotationPresent(annClass) && class2.isAnnotationPresent(annClass)) {
				ClassCompare classCompare1 = class1.getAnnotation(annClass);
				ClassCompare classCompare2 = class2.getAnnotation(annClass);
				return Integer.compare(classCompare1.order(), classCompare2.order());
			} else {
				rc = 0;
			}
		}
		if (direction == DESCENDING) {
			rc = -rc;
		}
		return rc;
	}

}
