package com.algaFood.algaFoodapi.domain.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaFood.algaFoodapi.domain.exception.EntidadeEmUsoException;
import com.algaFood.algaFoodapi.domain.exception.EntidadeNaoEncontradaExecption;
import com.algaFood.algaFoodapi.domain.model.Cidade;
import com.algaFood.algaFoodapi.domain.repository.CidadeRepository;

@Service
public class CidadeService implements CidadeRepository {
	
	@PersistenceContext
   private EntityManager manager;
    
    public List<Cidade> listar(){
        return manager.createQuery("from Cidade", Cidade.class).getResultList();

    }

	@Override
	public Cidade buscar(Long cidadeId) {
		TypedQuery query = manager.createQuery("select r from Cidade r where r.id = :cidadeId", Cidade.class);
		Cidade cidade =  (Cidade) query.setParameter("cidadeId", cidadeId).getSingleResult();
		 if(cidadeId == null) {
			 throw new EmptyResultDataAccessException(1);
		 }
		return cidade;
	}

	@Transactional
	@Override
	public Cidade adicionar(Cidade cidade) {
		manager.persist(cidade);
		 cidade = buscar(cidade.getId());
        return cidade;
	}

	@Transactional
	@Override
	public Cidade atualizar(Cidade cidade) {
		 manager.persist(cidade);
		 cidade = buscar(cidade.getId());
         return cidade;
	}

	@Transactional
	@Override
	public void remover(Long id) {
		
		try {
			Cidade cidadeId = buscar(id);
        	manager.remove(cidadeId);
        	
    	} catch (EmptyResultDataAccessException e){
             throw new EntidadeNaoEncontradaExecption(
               String.format("Não existe um cadastro de cidade com código %d", id));	
    		
		 } catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
			  String.format("Cidade de código %d não pode ser removido, pois está em uso", id));
		}
    	
		
	}

   
   
}
