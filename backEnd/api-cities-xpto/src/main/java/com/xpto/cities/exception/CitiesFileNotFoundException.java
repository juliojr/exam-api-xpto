package com.xpto.cities.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CitiesFileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7511631112459484797L;

	public CitiesFileNotFoundException(String message) {
		super(message);
	}

	public CitiesFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}