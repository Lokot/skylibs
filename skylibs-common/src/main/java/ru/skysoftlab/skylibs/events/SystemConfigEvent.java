package ru.skysoftlab.skylibs.events;

import java.util.Map;

/**
 * Событие изменения системных настроек.
 * 
 * @author Локтионов А.Г.
 *
 */
public class SystemConfigEvent {

	private final Map<String, Object> params;

	public SystemConfigEvent(Map<String, Object> params) {
		this.params = params;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	@SuppressWarnings("unchecked")
	public <T> T getParam(String key) {
		return (T) getParams().get(key);
	}

}
