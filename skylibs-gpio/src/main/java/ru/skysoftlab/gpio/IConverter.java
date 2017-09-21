package ru.skysoftlab.gpio;

public interface IConverter<P, PM, DP, S, SP> {
	
	public P convertPin(IPin p);
	
	public PM convertPinMode(PinMode p);
	
	public IDigitalPin convertToDigitalPin(DP digitalPin);
	
	public S convertSensor(ISensor sensor);
	
	public SP convertSensorParametr(ISensor sensor, ISensorParam param);
}
