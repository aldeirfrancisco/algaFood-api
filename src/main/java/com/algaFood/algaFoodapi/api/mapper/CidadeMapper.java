package com.algaFood.algaFoodapi.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaFood.algaFoodapi.api.model.dto.CidadeDTO;
import com.algaFood.algaFoodapi.api.model.input.cidade.CidadeInput;
import com.algaFood.algaFoodapi.domain.model.Cidade;
import com.algaFood.algaFoodapi.domain.model.Estado;

@Component
public class CidadeMapper {
	
	private final ModelMapper modelMapper;
	
	 public CidadeMapper(ModelMapper modelMapper) {
	        this.modelMapper = modelMapper;
	    }
	 
	 
	 public CidadeDTO toDto(Cidade cidade) {
		 return modelMapper.map(cidade, CidadeDTO.class);
	 }
	 
	 
	 public List<CidadeDTO> toCollectionDto(List<Cidade> cidades){

	     return cidades.stream().map(this::toDto).collect(Collectors.toList());

	 }
	 
	 
	 public Cidade toEntity(CidadeInput cidadeInput){

	      return modelMapper.map(cidadeInput, Cidade.class);

	 }
	 
	 public void copyToEntity(CidadeInput cidadeInput, Cidade cidade){

	        // Para evitar org.hibernate.HibernateException: identifier of an instance of
	        cidade.setEstado(new Estado());

	        modelMapper.map(cidadeInput, cidade);

	    }
}
