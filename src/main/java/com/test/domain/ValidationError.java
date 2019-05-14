package com.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class handle the field level validation.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidationError {

	/**
	 * Gets the resource.
	 *
	 * @return the resource
	 */
	
	/**
	 * Gets the resource.
	 *
	 * @return the resource
	 */
	@Getter

	/**
	 * Sets the resource.
	 *
	 * @param resource
	 *            the new resource
	 */
	
	/**
	 * Sets the resource.
	 *
	 * @param resource the new resource
	 */
	@Setter
	private String resource;

	/**
	 * Gets the field.
	 *
	 * @return the field
	 */
	
	/**
	 * Gets the field.
	 *
	 * @return the field
	 */
	@Getter

	/**
	 * Sets the field.
	 *
	 * @param field
	 *            the new field
	 */
	
	/**
	 * Sets the field.
	 *
	 * @param field the new field
	 */
	@Setter
	private String field;

	/**
	 * Gets the rejected value.
	 *
	 * @return the rejected value
	 */
	@Getter
	
	/**
	 * Sets the rejected value.
	 *
	 * @param rejectedValue the new rejected value
	 */
	@Setter
	private Object rejectedValue;

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	
	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	@Getter

	/**
	 * Sets the code.
	 *
	 * @param code
	 *            the new code
	 */
	
	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	@Setter
	private String code;

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	@Getter

	/**
	 * Sets the message.
	 *
	 * @param message
	 *            the new message
	 */
	
	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	@Setter
	private String message;

	/**
	 * Instantiates a new validation error.
	 */
	public ValidationError() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Builder
	public ValidationError(String resource, String field, Object rejectedValue, String code, String message) {
		super();
		this.resource = resource;
		this.field = field;
		this.rejectedValue=rejectedValue;
		this.code = code;
		this.message = message;
	}

}