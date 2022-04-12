package com.algaFood.algaFoodapi.api.model.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
public class ProdutoDTO {

	 private Long id;

	 private String nome;

	 private String descricao;

	 private BigDecimal preco;

	 private Boolean ativo;
}	
