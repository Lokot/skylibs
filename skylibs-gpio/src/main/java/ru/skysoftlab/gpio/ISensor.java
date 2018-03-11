package ru.skysoftlab.gpio;

public interface ISensor {

	byte getValue();

	ISensorParam getParametrByVal(byte parVal);

	ISensorParam[] getParams();

}