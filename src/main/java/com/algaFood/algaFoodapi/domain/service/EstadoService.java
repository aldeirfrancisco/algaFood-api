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
import com.algaFood.algaFoodapi.domain.model.Cozinha;
import com.algaFood.algaFoodapi.domain.model.Estado;
import com.algaFood.algaFoodapi.domain.model.Estado;
import com.algaFood.algaFoodapi.domain.model.Estado;
import com.algaFood.algaFoodapi.domain.repository.EstadoRepository;

@Service
public class EstadoService implements EstadoRepository {
	
	@PersistenceContext
   private EntityManager manager;
    
    public List<Estado> listar(){
        return manager.createQuery("from Estado", Estado.class).getResultList();

    }

	@Override
	public Estado buscar(Long estadoId) {
		TypedQuery query = manager.createQuery("select r from Estado r where r.id = :estadoId", Estado.class);
		Estado estado =  (Estado) query.setParameter("estadoId", estadoId).getSingleResult();
		 if(estado == null) {
			 throw new EmptyResultDataAccessException(1);
		 }
		return estado;
	}

	@Transactional
	@Override
	public Estado adicionar(Estado estado) {
		manager.persist(estado);
    	manager.flush();
        return estado;
	}

	@Transactional
	@Override
	public Estado atualizar(Estado estado) {
		 manager.persist(estado);
		 estado = buscar(estado.getId());
         return estado;
	}

	@Transactional
	@Override
	public void remover(Long id) {
		
		try {
			Estado estado = buscar(id);
        	manager.remove(estado);
        	
    	} catch (EmptyResultDataAccessException e){
             throw new EntidadeNaoEncontradaExecption(
               String.format("Não existe um cadastro de estado com código %d", id));	
    		
		 } catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
			  String.format("Estado de código %d não pode ser removido, pois está em uso", id));
		}
    	
		
	}

   
   
}
