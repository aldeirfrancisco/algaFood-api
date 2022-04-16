package com.algaFood.algaFoodapi.domain.exception;

public class FormaDePagamentoNaoEcontradaException extends EntidadeNaoEncontradaException{
	private static final long serialVersionUID = 1L;
	
	public FormaDePagamentoNaoEcontradaException(String msn) {
		super(msn);
	}
	
	   public FormaDePagamentoNaoEcontradaException(Long id) {
	        this(String.format("Não existe cadastro de forma de pagamento com o código %d", id));
	    }

}
