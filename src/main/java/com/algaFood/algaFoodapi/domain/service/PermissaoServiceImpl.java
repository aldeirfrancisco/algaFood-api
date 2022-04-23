package com.algaFood.algaFoodapi.domain.service;

import org.springframework.stereotype.Service;

import com.algaFood.algaFoodapi.domain.exception.PermissaoNaoEcontradaException;
import com.algaFood.algaFoodapi.domain.model.Permissao;
import com.algaFood.algaFoodapi.domain.repository.PermissaoRepository;
import com.algaFood.algaFoodapi.domain.service.serviceInterface.PermissaoService;

@Service
public class PermissaoServiceImpl implements PermissaoService {

    private final PermissaoRepository repository;

    public PermissaoServiceImpl(PermissaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Permissao buscarOuFalhar(Long id) {

        return repository.findById(id)
                .orElseThrow(()-> new PermissaoNaoEcontradaException(id));

    }
}