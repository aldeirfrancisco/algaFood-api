package com.algaFood.algaFoodapi.domain.service;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaFood.algaFoodapi.domain.exception.EntidadeEmUsoException;
import com.algaFood.algaFoodapi.domain.exception.EntidadeNaoEncontradaExecption;
import com.algaFood.algaFoodapi.domain.model.Estado;
import com.algaFood.algaFoodapi.domain.repository.EstadoRepository;

@Service
public class EstadoService {
	
	
   private EstadoRepository estadoRepository;
    public EstadoService(EstadoRepository estadoRepository) {
    	this.estadoRepository = estadoRepository;
    }
    public List<Estado> listar(){
        return estadoRepository.findAll();

    }


	public Estado buscar(Long estadoId) {
		return estadoRepository.findById(estadoId)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		
	}

	@Transactional
	public Estado adicionar(Estado estado) {
		return estadoRepository.save(estado);	
	}

	@Transactional
	public Estado atualizar(Estado estado) {
		return estadoRepository.save(estado);
	}

	@Transactional
	public void remover(Long id) {
		
		try {
        	estadoRepository.deleteById(id);
        	
    	} catch (EmptyResultDataAccessException e){
             throw new EntidadeNaoEncontradaExecption(
               String.format("Não existe um cadastro de estado com código %d", id));	
    		
		 } catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
			  String.format("Estado de código %d não pode ser removido, pois está em uso", id));
		}
    	
		
	}

   
   
}
