package ru.skysoftlab.gpio;

import java.io.Closeable;

public interface IGpioDevice extends IGpioDevicePinsPorts, Closeable {

	public void setPinMode(IPin pin, PinMode pinMode) throws GpioException;
	
	public short analogRead(IAnalogPin pin) throws GpioException;
	
	public String sensorRead(IDigitalPin pin, ISensor sensor, ISensorParam sParam, long delay) throws GpioException;
	
	public Boolean digitalRead(IDigitalPin pin) throws GpioException;
	
	public void digitalWrite(IDigitalPin pin, Boolean state) throws GpioException;
	
	public void delay(long miliseconds);
	
	public boolean isConnected();
	
}
