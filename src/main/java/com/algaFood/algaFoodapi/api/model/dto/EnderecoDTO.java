package com.algaFood.algaFoodapi.api.model.dto;

import lombok.Getter;
import lombok.Setter;

//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
public class EnderecoDTO {

	 private String cep;

	    private String logradouro;

	    private String numero;

	    private String complemento;

	    private String bairro;

	    private CidadeResumoDTO cidade;

	}
