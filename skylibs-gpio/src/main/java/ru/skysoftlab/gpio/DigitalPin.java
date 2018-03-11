package ru.skysoftlab.gpio;

public class DigitalPin extends Pin implements IDigitalPin {

	private static final long serialVersionUID = -1565877110979680386L;

	public DigitalPin() {
	}

	public DigitalPin(String name) {
		super(name);
	}

}
