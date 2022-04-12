package com.algaFood.algaFoodapi.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaFood.algaFoodapi.api.model.dto.UsuarioDTO;
import com.algaFood.algaFoodapi.api.model.input.usuario.UsuarioInput;
import com.algaFood.algaFoodapi.api.model.input.usuario.UsuarioUpdateInput;
import com.algaFood.algaFoodapi.domain.model.Usuario;

@Component
public class UsuarioMapper {

	private final ModelMapper modelMapper;

    public UsuarioMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public UsuarioDTO toDto(Usuario usuario){

        return modelMapper.map(usuario, UsuarioDTO.class);

    }

    public List<UsuarioDTO> toCollectionDto(List<Usuario> usuarios){

        return usuarios.stream().map(this::toDto).collect(Collectors.toList());

    }

    public Usuario toEntity(UsuarioInput usuarioInput) {

        return modelMapper.map(usuarioInput, Usuario.class);

    }

    public void copyToEntity(UsuarioUpdateInput usuarioUpdateInput, Usuario usuario) {

        modelMapper.map(usuarioUpdateInput, usuario);

    }
}
