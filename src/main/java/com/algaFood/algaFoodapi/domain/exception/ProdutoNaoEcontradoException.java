package com.algaFood.algaFoodapi.domain.exception;

public class ProdutoNaoEcontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public ProdutoNaoEcontradoException(String msg) {
        super(msg);
    }

    public ProdutoNaoEcontradoException(Long produtoId, Long restauranteId) {
        this(String.format("Não existe um cadastro de produto com código %d para o restaurante de código %d.",
                                                                                    produtoId, restauranteId));
    }

}
