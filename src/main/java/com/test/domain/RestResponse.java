package com.test.domain;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class RestResponse {

	@Getter
	@Setter
	Object data;
	
	@Getter
	@Setter
	boolean success;
	
	@Getter
	@Setter
	String message;
	
	@Getter
	@Setter
	List<ValidationError> errors;

	@Builder
	public RestResponse(Object data, boolean success, String message, List<ValidationError> errors) {
		super();
		this.data = data;
		this.success = success;
		this.message = message;
		this.errors = errors;
	}
	
}
