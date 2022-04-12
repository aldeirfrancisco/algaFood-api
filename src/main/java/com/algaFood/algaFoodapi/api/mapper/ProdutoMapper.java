package com.algaFood.algaFoodapi.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaFood.algaFoodapi.api.model.dto.ProdutoDTO;
import com.algaFood.algaFoodapi.api.model.input.produto.ProdutoInput;
import com.algaFood.algaFoodapi.domain.model.Produto;

@Component
public class ProdutoMapper {

	private final ModelMapper modelMapper;

    public ProdutoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProdutoDTO toDto(Produto produto){

        return modelMapper.map(produto, ProdutoDTO.class);

    }

    public List<ProdutoDTO> toCollectionDto(List<Produto> produtos){

        return produtos.stream().map(this::toDto).collect(Collectors.toList());

    }

    public Produto toEntity(ProdutoInput produtoInput){

        return modelMapper.map(produtoInput, Produto.class);

    }

    public void copyToEntity(ProdutoInput produtoInput, Produto produto){

        modelMapper.map(produtoInput, produto);

    }
}
