package com.algaFood.algaFoodapi.domain.exception;

public abstract class EntidadeNaoEncontradaExecption extends NegocioException {
	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaExecption(String msn) {
		super(msn);
	}

}
