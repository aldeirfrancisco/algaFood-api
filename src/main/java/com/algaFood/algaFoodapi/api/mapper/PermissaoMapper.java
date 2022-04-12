package com.algaFood.algaFoodapi.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaFood.algaFoodapi.api.model.dto.PermissaoDTO;
import com.algaFood.algaFoodapi.domain.model.Permissao;

@Component
public class PermissaoMapper {

	final ModelMapper mapper;

	public PermissaoMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public PermissaoDTO toDto(Permissao permissao){

        return mapper.map(permissao, PermissaoDTO.class);

    }

    public List<PermissaoDTO> toCollectionDto(List<Permissao> permissoes){

        return permissoes.stream().map(this::toDto).collect(Collectors.toList());

    }
}
