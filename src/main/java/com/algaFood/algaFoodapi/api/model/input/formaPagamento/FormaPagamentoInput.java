package com.algaFood.algaFoodapi.api.model.input.formaPagamento;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FormaPagamentoInput {

    @NotBlank
    private String descricao;

}
