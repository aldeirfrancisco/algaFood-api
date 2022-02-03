package com.algaFood.algaFoodapi.domain.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaFood.algaFoodapi.domain.model.Cozinha;
import com.algaFood.algaFoodapi.domain.model.Restaurante;
import com.algaFood.algaFoodapi.domain.repository.RestauranteRepository;

@Service
public class RestauranteService  implements RestauranteRepository{
	 @PersistenceContext
	 private EntityManager manager;
	
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

	@Override
	public Restaurante adicionar(Restaurante restaurante) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Restaurante atualizar(Restaurante restaurante) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remover(Long id) {
		// TODO Auto-generated method stub
		
	}
     
	

}
