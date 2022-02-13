package com.algaFood.algaFoodapi.domain.service;

import java.util.List;

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
public class RestauranteService {

	 private RestauranteRepository restauranteRepository;
	 
	 public RestauranteService(RestauranteRepository restauranteRepository){
		 this.restauranteRepository = restauranteRepository;
	 }
	 @Autowired
	 private CozinhaService cozinhaService;
	

	public List<Restaurante> listar() {
		 return restauranteRepository.findAll();
	}


	public Restaurante buscar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(()->new EmptyResultDataAccessException(1));
		
			
		
	}

	@Transactional
	public Restaurante adicionar(Restaurante restaurante) {
		Long idCozinha = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaService.buscar(idCozinha);
		 if(cozinha == null) {
			 throw new EntidadeNaoEncontradaExecption(
					 String.format("Não existe  cadastro de cozinha com código %d", idCozinha));
		 }
		 restaurante.setCozinha(cozinha);
		 return restauranteRepository.save(restaurante);
 
	}
    
	@Transactional
	public Restaurante atualizar(Restaurante restaurante) {
		Long idCozinha = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaService.buscar(idCozinha);
		 if(cozinha == null) {
			 throw new EntidadeNaoEncontradaExecption(
					 String.format("Não existe  cadastro de cozinha com código %d", idCozinha));
		 }
		 restaurante.setCozinha(cozinha);
		 return restauranteRepository.save(restaurante);
	}
    
	@Transactional

	public void remover(Long id) {
		
		try {
        	restauranteRepository.deleteById(id);
        	
    	} catch (EmptyResultDataAccessException e){
             throw new EntidadeNaoEncontradaExecption(
               String.format("Não existe um cadastro de restaurante com código %d", id));	
    		
		 } catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
			  String.format("Restaurante de código %d não pode ser removido, pois está em uso", id));
		}
    	
		
	}
     
	

}
