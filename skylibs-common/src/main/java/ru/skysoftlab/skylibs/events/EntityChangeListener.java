package ru.skysoftlab.skylibs.events;

import javax.enterprise.event.Observes;

public interface EntityChangeListener {

	public void entityChange(@Observes EntityChangeEvent event);
}
