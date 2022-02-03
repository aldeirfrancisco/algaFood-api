package com.algaFood.algaFoodapi.domain.exception;

public class EntidadeEmUsoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public EntidadeEmUsoException(String msn) {
		super(msn);
	}
	

}
