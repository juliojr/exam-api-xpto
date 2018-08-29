package com.xpto.cities.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.xpto.cities.payload.ErrorResponse;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(value = { CitiesFileException.class })
	public ResponseEntity<ErrorResponse> handleCitiesFileException(Exception ex, WebRequest request) {
		ErrorResponse response = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, ex.getMessage());
		return new ResponseEntity<ErrorResponse>(response, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(value = { DefaultXptoException.class })
	public ResponseEntity<ErrorResponse> handleCityModelException(DefaultXptoException ex, WebRequest request) {
		ErrorResponse response = new ErrorResponse(ex.getStatus(), ex.getMessage());
		return new ResponseEntity<ErrorResponse>(response, new HttpHeaders(), ex.getStatus());
	}

}
