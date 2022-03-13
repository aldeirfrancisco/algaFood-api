package com.algaFood.algaFoodapi.domain.exception;

public class CidadeNaoEncontradoException extends  EntidadeNaoEncontradaExecption {
	private static final long serialVersionUID = 1L;
	
	private static final String MSG_CIDADE_NAO_ENCONTRADA 
	   = "Não existe um cadastro de cidade com código %d";
	
	public CidadeNaoEncontradoException(String msn) {
		super(msn);
		
	}
	public CidadeNaoEncontradoException(Long id) {
		this(String.format(MSG_CIDADE_NAO_ENCONTRADA, id));
		
	}

	

}
