package ru.skysoftlab.gpio;

import java.util.Collection;

public interface IGpioDevicePinsPorts {

	public Collection<IDigitalPin> getAvalibleDigitalPins();

	public Collection<String> getCommPorts();

}
