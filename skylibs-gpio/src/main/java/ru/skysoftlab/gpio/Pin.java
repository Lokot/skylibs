package ru.skysoftlab.gpio;

public abstract class Pin implements IPin {

	private String name;
	
	public Pin() {
	}
	
	public Pin(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
