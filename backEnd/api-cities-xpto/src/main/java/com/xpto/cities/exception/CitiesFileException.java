package com.xpto.cities.exception;

public class CitiesFileException extends RuntimeException{
	
	private static final long serialVersionUID = -774090119972574999L;

	public CitiesFileException(String message) {
        super(message);
    }

    public CitiesFileException(String message, Throwable cause) {
        super(message, cause);
    }

}
