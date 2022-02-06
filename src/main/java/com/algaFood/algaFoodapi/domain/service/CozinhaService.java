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
public class CozinhaService implements CozinhaRepository {
	
	@PersistenceContext
   private EntityManager manager;
    
	@Override
    public List<Cozinha> listar(){
        return manager.createQuery("from Cozinha", Cozinha.class).getResultList();

    }

	@Override
    public Cozinha buscar(Long cozinhaId){
    	TypedQuery query = manager.createQuery("select c from Cozinha c where c.id = :cozinhaId ", Cozinha.class);
    	      query.setParameter("cozinhaId", cozinhaId);
    	      Cozinha cozinha = (Cozinha) query.getSingleResult();
    	      if(cozinha == null) {
    	    		throw new EmptyResultDataAccessException(1);
    	    	}
    	      return cozinha;
    	      
    }

    @Transactional
    @Override
    public Cozinha adicionar( Cozinha cozinha){
    	manager.persist(cozinha);
    	manager.flush();
        return cozinha;

    }

    @Transactional
    @Override
    public Cozinha atualizar(Cozinha cozinha) {
    	  manager.persist(cozinha);
    	  manager.flush();
          return cozinha;
    }

   
    @Transactional
    @Override
    public void remover(Long id) {
    	try {
    		Cozinha cozinha = buscar(id);
        	manager.remove(cozinha);
        	
    	} catch (EmptyResultDataAccessException e){
             throw new EntidadeNaoEncontradaExecption(
               String.format("Não existe um cadastro de cozinha com código %d", id));	
    		
		 } catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
			  String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
		}
    	
    	
    }


	
}
