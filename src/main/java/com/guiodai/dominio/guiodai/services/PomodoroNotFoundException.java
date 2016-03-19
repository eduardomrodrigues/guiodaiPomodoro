package com.guiodai.dominio.guiodai.services;

public class PomodoroNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public PomodoroNotFoundException() {
		super();
	}

	public PomodoroNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PomodoroNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PomodoroNotFoundException(String message) {
		super(message);
	}

	public PomodoroNotFoundException(Throwable cause) {
		super(cause);
	}

}
