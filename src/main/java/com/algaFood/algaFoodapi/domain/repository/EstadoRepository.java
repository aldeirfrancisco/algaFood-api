package com.algaFood.algaFoodapi.domain.repository;

import java.util.List;

import com.algaFood.algaFoodapi.domain.model.Estado;

public interface EstadoRepository {
	
       List<Estado> listar();  
       Estado buscar(Long estado);
       Estado adicionar(Estado estado);
       Estado atualizar(Estado estado);
       void remover(Long id);
}
