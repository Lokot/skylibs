package ru.skysoftlab.gpio;

public class GpioException extends Exception {

	private static final long serialVersionUID = 589474293214272210L;

	public GpioException() {
		super();
	}

	public GpioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GpioException(String message, Throwable cause) {
		super(message, cause);
	}

	public GpioException(String message) {
		super(message);
	}

	public GpioException(Throwable cause) {
		super(cause);
	}
	
}
