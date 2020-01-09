package com.tech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RequestException(String exception) {
		super(exception);
	}
	
}