package com.algaFood.algaFoodapi.domain.service.serviceInterface;

import com.algaFood.algaFoodapi.domain.model.Produto;

public interface ProdutoService {

    Produto salvar(Produto produto);

    Produto buscarOuFalhar(Long restauranteId, Long produtoId);

}
