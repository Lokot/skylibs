package ru.skysoftlab.skylibs.one.shot.scope.extension;

import java.io.Serializable;
import java.lang.annotation.Annotation;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.skysoftlab.skylibs.one.shot.scope.extension.OneShotScopeContextHolder.CustomScopeInstance;

public class OneShotScopeContext implements Context, Serializable {

	private static final long serialVersionUID = -3507195931382381471L;

	private final OneShotScopeContextHolder customScopeContextHolder;

	private final Logger LOG = LoggerFactory.getLogger(OneShotScopeContext.class);

	public OneShotScopeContext() {
		LOG.info("Init");
		this.customScopeContextHolder = OneShotScopeContextHolder.getInstance();
	}

	@Override
	public <T> T get(final Contextual<T> contextual) {
		return null;
	}

	@Override
	public <T> T get(final Contextual<T> contextual, final CreationalContext<T> creationalContext) {
		Bean<T> bean = (Bean<T>) contextual;
		if (customScopeContextHolder.getBeans().containsKey(bean.getBeanClass())) {
			customScopeContextHolder.destroyBean(customScopeContextHolder.getBean(bean.getBeanClass()));
		}
		T t = bean.create(creationalContext);
		CustomScopeInstance<T> customInstance = new CustomScopeInstance<>();
		customInstance.bean = bean;
		customInstance.ctx = creationalContext;
		customInstance.instance = t;
		customScopeContextHolder.putBean(customInstance);
		return t;
	}

	@Override
	public Class<? extends Annotation> getScope() {
		return OneShotScope.class;
	}

	@Override
	public boolean isActive() {
		return true;
	}

	public void passivate(@Observes KillEvent killEvent) {
		if (customScopeContextHolder.getBeans().containsKey(killEvent.getBeanType())) {
			customScopeContextHolder.destroyBean(customScopeContextHolder.getBean(killEvent.getBeanType()));
		}
	}
}