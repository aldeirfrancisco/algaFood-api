package com.algaFood.algaFoodapi.domain.exception;

public class EstadoNaoEncontradoException extends  EntidadeNaoEncontradaExecption {
	private static final long serialVersionUID = 1L;
	
	private static final String MSG_ESTADO_NAO_ENCONTRADA 
	   = "Não existe um cadastro de estado com código %d";
	
	public EstadoNaoEncontradoException(String msn) {
		super(msn);
		
	}
	public EstadoNaoEncontradoException(Long id) {
		this(String.format(MSG_ESTADO_NAO_ENCONTRADA, id));
		
	}

	

}
