package com.algaFood.algaFoodapi.api.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaFood.algaFoodapi.api.model.dto.FormaPagamentoDTO;
import com.algaFood.algaFoodapi.api.model.input.formaPagamento.FormaPagamentoInput;
import com.algaFood.algaFoodapi.domain.model.FormaPagamento;

@Component
public class FormaPagamentoMapper {

	final ModelMapper modelMapper;

	 public FormaPagamentoMapper(ModelMapper modelMapper) {
	        this.modelMapper = modelMapper;
	    }

	    public FormaPagamentoDTO toDto(FormaPagamento formaPagamento){

	        return modelMapper.map(formaPagamento, FormaPagamentoDTO.class);

	    }

	    public List<FormaPagamentoDTO> toCollectionDto(List<FormaPagamento> list){

	        return list.stream().map(this::toDto).collect(Collectors.toList());
	    }

	    public FormaPagamento toEntity (FormaPagamentoInput formasPagamentoInput){

	        return modelMapper.map(formasPagamentoInput, FormaPagamento.class);

	    }

	    public void copyToEntity(FormaPagamentoInput formaPagamentoInput, FormaPagamento formaPagamento){

	        modelMapper.map(formaPagamentoInput, formaPagamento);

	    }
}
