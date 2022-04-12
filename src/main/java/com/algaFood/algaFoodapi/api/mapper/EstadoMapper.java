package com.algaFood.algaFoodapi.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaFood.algaFoodapi.api.model.dto.EstadoDTO;
import com.algaFood.algaFoodapi.api.model.input.estado.EstadoInput;
import com.algaFood.algaFoodapi.domain.model.Estado;

@Component
public class EstadoMapper {

	final ModelMapper modelMapper;

    public EstadoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EstadoDTO toDto(Estado estado){

        return modelMapper.map(estado, EstadoDTO.class);

    }

    public List<EstadoDTO> toCollectionDto(List<Estado> estados){

        return estados.stream().map(this::toDto).collect(Collectors.toList());

    }

    public Estado toEntity(EstadoInput estadoInput){

        return modelMapper.map(estadoInput, Estado.class);

    }

    public void copyToEntity(EstadoInput estadoInput, Estado estado){

        modelMapper.map(estadoInput, estado);

    }
}
