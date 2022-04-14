package com.algaFood.algaFoodapi.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaFood.algaFoodapi.api.model.dto.CozinhaDTO;
import com.algaFood.algaFoodapi.api.model.input.cozinha.CozinhaInput;
import com.algaFood.algaFoodapi.domain.model.Cozinha;

@Component
public class CozinhaMapper {

    final ModelMapper modelMapper;

    public CozinhaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CozinhaDTO toDto(Cozinha cozinha){

        return modelMapper.map(cozinha, CozinhaDTO.class);

    }

    public List<CozinhaDTO> toCollectionDto(List<Cozinha> cozinhas){

        return cozinhas.stream().map(this::toDto).collect(Collectors.toList());
    }


    public Cozinha toEntity(CozinhaDTO cozinha){

        return modelMapper.map(cozinha, Cozinha.class);

    }

    public void copyToEntity(CozinhaInput cozinhaInput, Cozinha cozinha){

        modelMapper.map(cozinhaInput, cozinha);

    }
}
