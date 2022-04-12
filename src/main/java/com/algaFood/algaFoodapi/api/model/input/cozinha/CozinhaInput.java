package com.algaFood.algaFoodapi.api.model.input.cozinha;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInput {

    @NotBlank
    private String nome;
}
