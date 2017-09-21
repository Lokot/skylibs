package ru.skysoftlab.gpio;

public interface IAnalogPin extends IPin {
	public default PinType getPinType() {
		return PinType.ANALOG;
	}
}
