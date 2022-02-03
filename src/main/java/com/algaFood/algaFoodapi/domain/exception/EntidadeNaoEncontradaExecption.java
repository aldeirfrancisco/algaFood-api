package com.algaFood.algaFoodapi.domain.exception;

public class EntidadeNaoEncontradaExecption extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaExecption(String msn) {
		super(msn);
	}

}
