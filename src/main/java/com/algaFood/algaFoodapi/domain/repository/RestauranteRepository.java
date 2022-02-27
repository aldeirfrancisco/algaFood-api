package com.algaFood.algaFoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.algaFood.algaFoodapi.domain.model.Restaurante;

@Repository
public interface RestauranteRepository 
    extends JpaRepository<Restaurante, Long>,  RestauranteRepositoryQueries,
     JpaSpecificationExecutor<Restaurante>{
	
     
}
