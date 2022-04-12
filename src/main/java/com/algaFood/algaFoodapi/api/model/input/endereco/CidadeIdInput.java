package com.algaFood.algaFoodapi.api.model.input.endereco;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeIdInput {

    @NotNull
    private Long id;

}
