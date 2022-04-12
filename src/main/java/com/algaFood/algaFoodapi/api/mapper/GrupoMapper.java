package com.algaFood.algaFoodapi.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaFood.algaFoodapi.api.model.dto.GrupoDTO;
import com.algaFood.algaFoodapi.api.model.input.grupo.GrupoInput;
import com.algaFood.algaFoodapi.domain.model.Grupo;

@Component
public class GrupoMapper {

	final ModelMapper modelMapper;

	public GrupoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public GrupoDTO toDto(Grupo grupo){

        return modelMapper.map(grupo, GrupoDTO.class);

    }

    public List<GrupoDTO> toCollectionDto(List<Grupo> grupos){

        return grupos.stream().map(this::toDto).collect(Collectors.toList());

    }

    public Grupo toEntity(GrupoInput grupoInput){

        return modelMapper.map(grupoInput, Grupo.class);

    }

    public void copyToEntity(GrupoInput grupoInput, Grupo grupo){

        modelMapper.map(grupoInput, grupo);

    }
}
