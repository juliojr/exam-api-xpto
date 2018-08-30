package com.xpto.cities.exception;

import org.springframework.http.HttpStatus;

public class DefaultXptoException extends RuntimeException {

	private static final long serialVersionUID = -5529883313798608664L;

	private HttpStatus status;

	public DefaultXptoException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public DefaultXptoException(String message, Throwable cause, HttpStatus status) {
		super(message, cause);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
