package com.guiodai.dominio.github.exceptions;

public class UnauthorizedException extends Exception{

	public UnauthorizedException() {
		super();
	}

	public UnauthorizedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnauthorizedException(String message) {
		super(message);
	}

	public UnauthorizedException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 1L;

}
