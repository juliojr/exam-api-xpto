package com.xpto.cities.exception;

public class CityModelException extends RuntimeException{

	private static final long serialVersionUID = -5529883313798608664L;
	
	
	public CityModelException(String message) {
        super(message);
    }

    public CityModelException(String message, Throwable cause) {
        super(message, cause);
    }
	

}
