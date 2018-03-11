package ru.skysoftlab.gpio.cdi;

import java.io.IOException;

import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.skysoftlab.gpio.IGpioDevice;

public abstract class AbstractGpioDevice implements IGpioDevice {
	
	private Logger LOG = LoggerFactory.getLogger(getClass());

	@Inject
	private javax.enterprise.event.Event<DeviceConnectedEvent> deviceConnectEvent;

	@Override
	public void fireDeviceConnectedEvent() {
		deviceConnectEvent.fire(new DeviceConnectedEvent());
	}
	
	@PreDestroy
	private void destroy() {
		try {
			close();
		} catch (IOException e) {
			LOG.error("Error to destroy device", e);
		}
	}
}
