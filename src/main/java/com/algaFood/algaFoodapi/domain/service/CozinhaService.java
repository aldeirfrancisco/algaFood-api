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

    private static final String MSG_COZINHA_EM_USO 
          = "Cozinha de código %d não pode ser removida, pois está em uso";

	private static final String MSG_COZINHA_NAO_ENCONTRADA 
          = "Não existe um cadastro de cozinha com código %d";
    
    private CozinhaRepository cozinhaRepository;
    
    public CozinhaService(CozinhaRepository cozinhaRepository) {
    	this.cozinhaRepository = cozinhaRepository;
    }

    public List<Cozinha> listar(){
        return cozinhaRepository.findAll();

    }

    public Cozinha buscar(Long cozinhaId){
         return cozinhaRepository.findById(cozinhaId)
        		 .orElseThrow(() -> new EntidadeNaoEncontradaExecption(
        	               String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId)));	      
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
        	cozinhaRepository.flush();
        	
    	} catch (EmptyResultDataAccessException e){
    		
             throw new EntidadeNaoEncontradaExecption(
               String.format(MSG_COZINHA_NAO_ENCONTRADA, id));	
    		
		 } catch (DataIntegrityViolationException e) {
			 
			throw new EntidadeEmUsoException(
			  String.format(MSG_COZINHA_EM_USO, id));
		}
    	
    	
    }


	
}
