package com.algaFood.algaFoodapi.domain.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaFood.algaFoodapi.api.mapper.CozinhaMapper;
import com.algaFood.algaFoodapi.api.model.dto.CozinhaDTO;
import com.algaFood.algaFoodapi.api.model.input.cozinha.CozinhaInput;
import com.algaFood.algaFoodapi.api.model.input.restaurante.CozinhaIdInput;
import com.algaFood.algaFoodapi.domain.exception.CidadeNaoEncontradoException;
import com.algaFood.algaFoodapi.domain.exception.CozinhaNaoEncontradoException;
import com.algaFood.algaFoodapi.domain.exception.EntidadeEmUsoException;
import com.algaFood.algaFoodapi.domain.model.Cozinha;
import com.algaFood.algaFoodapi.domain.repository.CozinhaRepository;

@Service
public class CozinhaService  {

    private static final String MSG_COZINHA_EM_USO 
          = "Cozinha de código %d não pode ser removida, pois está em uso";
    
    private CozinhaRepository cozinhaRepository;
    
    private final CozinhaMapper mapper;
    
    public CozinhaService(CozinhaRepository cozinhaRepository, CozinhaMapper mapper) {
    	this.cozinhaRepository = cozinhaRepository;
    	this.mapper = mapper;
    }

    public List<CozinhaDTO> listar(){
    	var cozinha = cozinhaRepository.findAll();
        return mapper.toCollectionDto(cozinha); 

    }

    public Cozinha buscar(Long cozinhaId){
    	return cozinhaRepository.findById(cozinhaId)
        		 .orElseThrow(() -> new CidadeNaoEncontradoException(cozinhaId));
        
    }
    public CozinhaDTO buscarDTO(Long cozinhaId){
    	var cozinha = cozinhaRepository.findById(cozinhaId)
        		 .orElseThrow(() -> new CidadeNaoEncontradoException(cozinhaId));
    	return mapper.toDto(cozinha);
        
    }

    @Transactional
    public CozinhaDTO adicionar( CozinhaInput cozinhainput){
    	var cozinha = mapper.toEntity(cozinhainput);
    	return mapper.toDto(cozinhaRepository.save(cozinha));
    }

    @Transactional
    public CozinhaDTO  atualizar(Cozinha cozinha) {
    	var co = cozinhaRepository.save(cozinha);
    	return mapper.toDto(co);
    }

   
    @Transactional
    public void remover(Long id) {
    	try {
    		
        	cozinhaRepository.deleteById(id);
        	cozinhaRepository.flush();
        	
    	} catch (EmptyResultDataAccessException e){
    		
             throw  new CozinhaNaoEncontradoException(id);	
    		
		 } catch (DataIntegrityViolationException e) {
			 
			throw new EntidadeEmUsoException(
			  String.format(MSG_COZINHA_EM_USO, id));
		}
    	
    	
    }


	
}
