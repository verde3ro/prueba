package com.apps.exception;

public class InternalException extends RuntimeException{

	private static final long serialVersionUID = 7301083137847841563L;

	public InternalException( String message, Throwable cause, Object... params) {
		super(String.format(message, params), cause, false, true);
	}

	public InternalException(String message, Object... params) {
		this(message, null, params);
	}

}
