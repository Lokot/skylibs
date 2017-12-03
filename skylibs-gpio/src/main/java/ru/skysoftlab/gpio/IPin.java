package ru.skysoftlab.gpio;

import java.io.Serializable;

public interface IPin extends Serializable {

	public String getName();

	public PinType getPinType();

}
