package com.algaFood.algaFoodapi.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaFood.algaFoodapi.api.model.dto.EnderecoDTO;
import com.algaFood.algaFoodapi.domain.model.Endereco;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        var modelMapper = new ModelMapper();

     //   modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class)
     //        .addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setPrecoFrete);

       var enderecoToEnderecoModelTypeMap =  modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class);

       enderecoToEnderecoModelTypeMap.<String>addMapping(enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
               (enderecoDtoDest, value) -> enderecoDtoDest.getCidade().setEstado(value));

        return modelMapper;
    }
}

