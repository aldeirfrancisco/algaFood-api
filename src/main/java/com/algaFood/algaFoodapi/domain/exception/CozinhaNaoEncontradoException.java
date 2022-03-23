package com.algaFood.algaFoodapi.domain.exception;

public class CozinhaNaoEncontradoException extends  EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;
	
	private static final String MSG_COZINHA_NAO_ENCONTRADA 
	   = "Não existe um cadastro de cozinha com código %d";
	
	public CozinhaNaoEncontradoException(String msn) {
		super(msn);
		
	}
	public CozinhaNaoEncontradoException(Long id) {
		this(String.format(MSG_COZINHA_NAO_ENCONTRADA, id));
		
	}

	

}
