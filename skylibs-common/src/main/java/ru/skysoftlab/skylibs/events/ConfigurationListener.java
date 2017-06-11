package ru.skysoftlab.skylibs.events;

import javax.enterprise.event.Observes;

import ru.skysoftlab.skylibs.events.SystemConfigEvent;

/**
 * Слушатель изменения конфигурации.
 * 
 * @author Lokot
 *
 */
public interface ConfigurationListener {

	/**
	 * Слушатель событий.
	 * 
	 * @param event
	 *            событие изменения системных настроек
	 */
	public void editIntervalEvent(@Observes SystemConfigEvent event);

}
