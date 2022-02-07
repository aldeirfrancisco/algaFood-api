package com.algaFood.algaFoodapi.domain.repository;

import java.util.List;

import com.algaFood.algaFoodapi.domain.model.Cidade;

public interface CidadeRepository {
	
       List<Cidade> listar();  
       Cidade buscar(Long cidade);
       Cidade adicionar(Cidade cidade);
       Cidade atualizar(Cidade cidade);
       void remover(Long id);
}
