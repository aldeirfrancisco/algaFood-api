package com.algaFood.algaFoodapi.domain.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaFood.algaFoodapi.domain.exception.EntidadeEmUsoException;
import com.algaFood.algaFoodapi.domain.exception.EntidadeNaoEncontradaExecption;
import com.algaFood.algaFoodapi.domain.model.Cozinha;
import com.algaFood.algaFoodapi.domain.model.Restaurante;
import com.algaFood.algaFoodapi.domain.repository.RestauranteRepository;

@Service
public class RestauranteService  implements RestauranteRepository{
	 @PersistenceContext
	 private EntityManager manager;
	 @Autowired
	 private CozinhaService cozinhaService;
	
	@Override
	public List<Restaurante> listar() {
		 return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
	}

	@Override
	public Restaurante buscar(Long restauranteId) {
		TypedQuery query = manager.createQuery("select r from Restaurante r where r.id = :restauranteId", Restaurante.class);
		Restaurante restaurante =  (Restaurante) query.setParameter("restauranteId", restauranteId).getSingleResult();
		 if(restaurante == null) {
			 throw new EmptyResultDataAccessException(1);
		 }
		return restaurante;
	}

	@Transactional
	@Override
	public Restaurante adicionar(Restaurante restaurante) {
		Long idCozinha = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaService.buscar(idCozinha);
		 if(cozinha == null) {
			 throw new EntidadeNaoEncontradaExecption(
					 String.format("Não existe  cadastro de cozinha com código %d", idCozinha));
		 }
		 restaurante.setCozinha(cozinha);
		manager.persist(restaurante);
    	manager.flush();
        return restaurante;
	}
    
	@Transactional
	@Override
	public Restaurante atualizar(Restaurante restaurante) {
		Long idCozinha = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaService.buscar(idCozinha);
		 if(cozinha == null) {
			 throw new EntidadeNaoEncontradaExecption(
					 String.format("Não existe  cadastro de cozinha com código %d", idCozinha));
		 }
		 restaurante.setCozinha(cozinha);
		 manager.persist(restaurante);
   	     Restaurante rest = buscar(restaurante.getId());
         return rest;
	}
    
	@Transactional
	@Override
	public void remover(Long id) {
		
		try {
			Restaurante restaurante = buscar(id);
        	manager.remove(restaurante);
        	
    	} catch (EmptyResultDataAccessException e){
             throw new EntidadeNaoEncontradaExecption(
               String.format("Não existe um cadastro de restaurante com código %d", id));	
    		
		 } catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
			  String.format("Restaurante de código %d não pode ser removido, pois está em uso", id));
		}
    	
		
	}
     
	

}
