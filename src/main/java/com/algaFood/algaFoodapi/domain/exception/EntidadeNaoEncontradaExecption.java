package com.algaFood.algaFoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =  HttpStatus.NOT_FOUND)  //reason = "entidade nao encontrada"
public abstract class EntidadeNaoEncontradaExecption extends NegocioException {
	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaExecption(String msn) {
		super(msn);
	}

}
