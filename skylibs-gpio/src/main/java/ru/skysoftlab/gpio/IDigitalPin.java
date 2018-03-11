package ru.skysoftlab.gpio;

public interface IDigitalPin extends IPin {
	
	public default PinType getPinType() {
		return PinType.DIGITAL;
	}

}
