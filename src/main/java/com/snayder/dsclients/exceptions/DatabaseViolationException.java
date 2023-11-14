package com.snayder.dsclients.exceptions;

public class DatabaseViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DatabaseViolationException(String message) {
		super(message);
	}

}
