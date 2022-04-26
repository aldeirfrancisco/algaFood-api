package com.algaFood.algaFoodapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algaFood.algaFoodapi.domain.exception.ProdutoNaoEcontradoException;
import com.algaFood.algaFoodapi.domain.model.Produto;
import com.algaFood.algaFoodapi.domain.repository.ProdutoRepository;
import com.algaFood.algaFoodapi.domain.service.serviceInterface.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoServiceImpl(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    @Override
    public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {

        return repository.findById(restauranteId, produtoId)
                .orElseThrow(()-> new ProdutoNaoEcontradoException(produtoId, restauranteId));

    }
}
