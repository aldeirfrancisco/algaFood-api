package com.algaFood.algaFoodapi.domain.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.algaFood.algaFoodapi.domain.model.Produto;
import com.algaFood.algaFoodapi.domain.model.Restaurante;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
  

    @Query("from Produto where restaurante.id = :restaurante and id = :produto")
    Optional<Produto> findById(@Param("restaurante") Long restauranteId, @Param("produto") Long produtoId);

    List<Produto> findByRestaurante(Restaurante restaurante);
}
