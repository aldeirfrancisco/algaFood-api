package com.algaFood.algaFoodapi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaFood.algaFoodapi.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends  CustomJpaRepository<Restaurante, Long>,
    RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante>{
	
	@Query("FROM Restaurante r JOIN FETCH r.cozinha JOIN FETCH r.formasPagamento")
	List<Restaurante>findAll();
     
}
