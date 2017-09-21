package ru.skysoftlab.gpio;

public interface ISensorParam {

	public boolean isIt(byte val);
	
	public byte getValue();
}
