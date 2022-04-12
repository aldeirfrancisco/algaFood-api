package com.algaFood.algaFoodapi.api.model.input.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsuarioUpdateInput {

	    @NotBlank
	    private String nome;

	    @NotBlank
	    @Email
	    private String email;
}