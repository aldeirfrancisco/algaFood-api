package com.algaFood.algaFoodapi.api.model.dto;

import lombok.Getter;
import lombok.Setter;

//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
public class CidadeDTO {

    private Long id;

    private String nome;

    private EstadoDTO estado;

}	
