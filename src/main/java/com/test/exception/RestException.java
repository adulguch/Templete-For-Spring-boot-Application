package com.test.exception;

import java.util.ArrayList;
import java.util.List;

import com.test.domain.ValidationError;

import lombok.Getter;

public class RestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The error message. */
	@Getter
	private String errorMessage;
	@Getter
	private String resource;

	@Getter
	private List<ValidationError> fieldErrors = new ArrayList<ValidationError>();

	/**
	 * Instantiates a new resource not found exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public RestException(String resource, String message, Throwable cause) {
		super(message, cause);
		this.resource = resource;
		this.errorMessage = message;
	}

	/**
	 * Instantiates a new resource not found exception.
	 *
	 * @param message
	 *            the message
	 */
	public RestException(String resource, String message) {
		super(message);
		this.resource = resource;
		this.errorMessage = message;
	}

	public boolean addFieldErrors(ValidationError field) {
		return this.fieldErrors.add(field);
	}

	/**
	 * Instantiates a new resource not found exception.
	 */
	public RestException() {
	}

}
