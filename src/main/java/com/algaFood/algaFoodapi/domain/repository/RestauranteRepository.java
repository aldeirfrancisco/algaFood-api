package com.algaFood.algaFoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.algaFood.algaFoodapi.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends  CustomJpaRepository<Restaurante, Long>,
    RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante>{
	
     
}
