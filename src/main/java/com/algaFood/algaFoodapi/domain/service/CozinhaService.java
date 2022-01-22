package com.algaFood.algaFoodapi.domain.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.algaFood.algaFoodapi.domain.model.Cozinha;

@Service
public class CozinhaService {
	@PersistenceContext
   private EntityManager manager;
    
    public List<Cozinha> listar(){
        return manager.createQuery("from Cozinha", Cozinha.class).getResultList();

    }

   
    public Cozinha buscar(@PathVariable Long cozinhaId){

        return null;

    }

  
    public Cozinha adicionar( Cozinha cozinha){

        return null;

    }

   
    public Cozinha atualizar(Cozinha cozinha) {

        return null;

    }

   
  
    public void remover(Cozinha cozinha) {

    }
}
