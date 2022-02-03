package com.algaFood.algaFoodapi.domain.repository;

import java.util.List;

import com.algaFood.algaFoodapi.domain.model.Cozinha;
import com.algaFood.algaFoodapi.domain.model.Restaurante;

public interface RestauranteRepository {
	
       List<Restaurante> listar();  
       Restaurante buscar(Long restauranteId);
       Restaurante adicionar(Restaurante restaurante);
       Restaurante atualizar(Restaurante restaurante);
       void remover(Long id);
}
