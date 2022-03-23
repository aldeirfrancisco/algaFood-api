package com.algaFood.algaFoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum UserMessage {
  
	    MSG_ERRO_GENERICA("Ocorreu um erro interno inesperado no sistema"),
	    MSG_DADOS_INVALIDOS("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente."),
	    MSG_OPERACAO_NAO_PERMITIDA("Operação não permitida"),
	    MSG_VIOLACAO_REGRA_NEGOCIO("Violação de regra de negócio"),
	    MSG_RECURSO_NAO_ENCONTRADO("Recurso não encontrado");

	    private String mensagem;

	    UserMessage(String mensagem){

	        this.mensagem = mensagem;

	    }
}
