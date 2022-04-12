package com.algaFood.algaFoodapi.api.model.input.estado;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInput {

    @NotBlank
    private String nome;

}
