package com.algaFood.algaFoodapi.domain.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaFood.algaFoodapi.domain.exception.EntidadeEmUsoException;
import com.algaFood.algaFoodapi.domain.exception.EntidadeNaoEncontradaExecption;
import com.algaFood.algaFoodapi.domain.model.Cozinha;
import com.algaFood.algaFoodapi.domain.repository.CozinhaRepository;

@Service
public class CozinhaService  {

   private CozinhaRepository cozinhaRepository;
    public CozinhaService(CozinhaRepository cozinhaRepository) {
    	this.cozinhaRepository = cozinhaRepository;
    }

    public List<Cozinha> listar(){
        return cozinhaRepository.findAll();

    }

    public Cozinha buscar(Long cozinhaId){
         return cozinhaRepository.findById(cozinhaId)
        		 .orElseThrow(() -> new EmptyResultDataAccessException(1));	      
    }

    @Transactional
    public Cozinha adicionar( Cozinha cozinha){
    	return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public Cozinha atualizar(Cozinha cozinha) {
    	return cozinhaRepository.save(cozinha);
    }

   
    @Transactional
    public void remover(Long id) {
    	try {
        	cozinhaRepository.deleteById(id);
        	
    	} catch (EmptyResultDataAccessException e){
             throw new EntidadeNaoEncontradaExecption(
               String.format("Não existe um cadastro de cozinha com código %d", id));	
    		
		 } catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
			  String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
		}
    	
    	
    }


	
}
