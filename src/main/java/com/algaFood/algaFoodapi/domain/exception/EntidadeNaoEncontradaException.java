package com.algaFood.algaFoodapi.domain.exception;

public abstract class EntidadeNaoEncontradaException extends NegocioException {
	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaException(String msn) {
		super(msn);
	}

}
