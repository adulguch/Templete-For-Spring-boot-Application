package com.test.exception;

import lombok.ToString;

/**
 * The Class ResourceNotFoundException.
 */
@ToString
public class SqlQueryNotFoundException extends Exception {
	
	/** The Constant serialVersionUUID. */
	private static final long serialVersionUID = 1l;
	
	/** The error message. */
	private String errorMessage;

	/**
	 * Instantiates a new resource not found exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public SqlQueryNotFoundException(String message, Throwable cause) {
		super(message, cause);
		this.errorMessage = message;
	}

	/**
	 * Instantiates a new resource not found exception.
	 *
	 * @param message the message
	 */
	public SqlQueryNotFoundException(String message) {
		super(message);
		this.errorMessage = message;
	}

	/**
	 * Instantiates a new resource not found exception.
	 */
	public SqlQueryNotFoundException() {
	}

}