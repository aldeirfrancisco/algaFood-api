package com.algaFood.algaFoodapi.api.model.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class RestauranteDTO {

	 private Long id;

	 private String nome;

	 private BigDecimal taxaFrete;

	 private CozinhaDTO cozinha;

	 private Boolean ativo;

	 private Boolean aberto;

	 private EnderecoDTO endereco;

}	
