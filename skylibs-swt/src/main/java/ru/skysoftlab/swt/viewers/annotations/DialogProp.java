package ru.skysoftlab.swt.viewers.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.eclipse.swt.widgets.Control;

import ru.skysoftlab.swt.control.IControlAdapter;
import ru.skysoftlab.swt.control.adapters.TextAdapter;

@Retention(RUNTIME)
@Target(METHOD)
public @interface DialogProp {
	int index();
	String name();
	Class<? extends IControlAdapter<? extends Control>> adapter() default TextAdapter.class;
	boolean editable() default true;
}
