package com.guiodai.dominio.github.exceptions;

public class BadRequestException extends Exception {

	public BadRequestException() {
		super();
	}

	public BadRequestException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 1L;

}
