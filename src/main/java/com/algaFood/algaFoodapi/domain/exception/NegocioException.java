package com.algaFood.algaFoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =  HttpStatus.BAD_REQUEST) 
public class NegocioException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NegocioException(String msn) {
		super(msn);
	}
	
	public NegocioException(String msn, Throwable causa ) {
		super(msn, causa);
	}

}
