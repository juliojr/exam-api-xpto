package com.xpto.cities.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CitiesFileException extends RuntimeException{
	
	private static final long serialVersionUID = -774090119972574999L;

	public CitiesFileException(String message) {
        super(message);
    }

    public CitiesFileException(String message, Throwable cause) {
        super(message, cause);
    }

}

