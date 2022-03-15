package com.algaFood.algaFoodapi.domain.exception;

public class NegocioException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NegocioException(String msn) {
		super(msn);
	}
	
	public NegocioException(String msn, Throwable causa ) {
		super(msn, causa);
	}

}
