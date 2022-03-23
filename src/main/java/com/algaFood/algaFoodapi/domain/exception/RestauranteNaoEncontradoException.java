package com.algaFood.algaFoodapi.domain.exception;

public class RestauranteNaoEncontradoException extends  EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;
	
	private static final String MSG_RESTAURANTE_NAO_ENCONTRADA 
	   = "Não existe um cadastro de restaurante com código %d";
	
	public RestauranteNaoEncontradoException(String msn) {
		super(msn);
		
	}
	public RestauranteNaoEncontradoException(Long id) {
		this(String.format(MSG_RESTAURANTE_NAO_ENCONTRADA, id));
		
	}

	

}
