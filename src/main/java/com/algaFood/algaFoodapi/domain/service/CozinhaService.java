package com.algaFood.algaFoodapi.domain.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaFood.algaFoodapi.domain.model.Cozinha;

@Service
public class CozinhaService {
	@PersistenceContext
   private EntityManager manager;
    
    public List<Cozinha> listar(){
        return manager.createQuery("from Cozinha", Cozinha.class).getResultList();

    }

   
    public Cozinha buscar(Long cozinhaId){
    	TypedQuery query = manager.createQuery("select c from Cozinha c where c.id = :cozinhaId ", Cozinha.class);
    	      query.setParameter("cozinhaId", cozinhaId);
    	return  (Cozinha) query.getSingleResult();
    }

    @Transactional
    public Cozinha adicionar( Cozinha cozinha){
    	manager.persist(cozinha);
    	manager.flush();
        return cozinha;

    }

    @Transactional
    public Cozinha atualizar(Cozinha cozinha) {
    	  manager.persist(cozinha);
    	  manager.flush();
          return cozinha;
    }

   
    @Transactional
    public void remover(Cozinha coz) {
    	manager.remove(coz);
    }
}
