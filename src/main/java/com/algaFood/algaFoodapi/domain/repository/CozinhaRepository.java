package com.algaFood.algaFoodapi.domain.repository;

import java.util.List;

import com.algaFood.algaFoodapi.domain.model.Cozinha;

public interface CozinhaRepository {
	
       List<Cozinha> listar();  
       Cozinha buscar(Long cozinhaId);
       Cozinha adicionar( Cozinha cozinha);
       Cozinha atualizar(Cozinha cozinha);
       void remover(Long id);
}
