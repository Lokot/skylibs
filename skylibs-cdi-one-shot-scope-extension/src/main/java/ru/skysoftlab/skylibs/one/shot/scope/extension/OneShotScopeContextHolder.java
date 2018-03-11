package ru.skysoftlab.skylibs.one.shot.scope.extension;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;

@SuppressWarnings("rawtypes")
public class OneShotScopeContextHolder implements Serializable {

	/**
	 * wrap necessary properties so we can destroy the bean later:
	 *
	 * @see OneShotScopeContextHolder#destroyBean(OneShotScopeContextHolder.scope.extension.CustomScopeContextHolder.CustomScopeInstance)
	 */
	public static class CustomScopeInstance<T> {

		Bean<T> bean;
		CreationalContext<T> ctx;
		T instance;
	}

	private static OneShotScopeContextHolder INSTANCE;
	private static final long serialVersionUID = -1126938834606816399L;

	public synchronized static OneShotScopeContextHolder getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new OneShotScopeContextHolder();
		}
		return INSTANCE;
	}

	private final Map<Class, CustomScopeInstance> beans;// we will have only one instance of a type so the key is a
														// class

	private OneShotScopeContextHolder() {
		beans = Collections.synchronizedMap(new HashMap<Class, CustomScopeInstance>());
	}

	@SuppressWarnings("unchecked")
	void destroyBean(CustomScopeInstance customScopeInstance) {
		getBeans().remove(customScopeInstance.bean.getBeanClass());
		customScopeInstance.bean.destroy(customScopeInstance.instance, customScopeInstance.ctx);
	}

	public CustomScopeInstance getBean(Class type) {
		return getBeans().get(type);
	}

	public Map<Class, CustomScopeInstance> getBeans() {
		return beans;
	}

	public void putBean(CustomScopeInstance customInstance) {
		getBeans().put(customInstance.bean.getBeanClass(), customInstance);
	}
}
