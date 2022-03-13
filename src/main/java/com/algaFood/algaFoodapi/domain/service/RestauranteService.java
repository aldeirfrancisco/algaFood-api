package com.algaFood.algaFoodapi.domain.service;

import java.math.BigDecimal;
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

	 private static final String MSG_RESTAURANTE_EM_USO 
	       = "Restaurante de código %d não pode ser removido, pois está em uso";

	private static final String MSG_RESTAURANTE_NAO_ENCONTRADA 
	       = "Não existe um cadastro de restaurante com código %d";

	private static final String MSG_COZINHA_NAO_ENCONTRADA 
	       = "Não existe  cadastro de cozinha com código %d";
	 
	 private RestauranteRepository restauranteRepository;
	 
	 public RestauranteService(RestauranteRepository restauranteRepository){
		 this.restauranteRepository = restauranteRepository;
	 }
	 
	 @Autowired
	 private CozinhaService cozinhaService;
	

	public List<Restaurante> listar() {
		 return restauranteRepository.findAll();
	}
	
	public List<Restaurante> restaurantePorNomeFrete(String nome, BigDecimal taxaFreteInicial, BigDecimal         					taxaFreteFinal) {
		 return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}


	public Restaurante buscar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(()->new EntidadeNaoEncontradaExecption(
			               String.format(MSG_RESTAURANTE_NAO_ENCONTRADA, restauranteId)));
		
			
		
	}

	@Transactional
	public Restaurante adicionar(Restaurante restaurante) {
		Long idCozinha = restaurante.getCozinha().getId();
		 restaurante.setCozinha( cozinhaService.buscar(idCozinha) );
		 return restauranteRepository.save(restaurante);
 
	}
    
	@Transactional
	public Restaurante atualizar(Restaurante restaurante) {
		Long idCozinha = restaurante.getCozinha().getId();
		 restaurante.setCozinha(cozinhaService.buscar(idCozinha));
		 return restauranteRepository.save(restaurante);
	}
    
	@Transactional

	public void remover(Long id) {
		
		try {
			
        	restauranteRepository.deleteById(id);
        	restauranteRepository.flush();
        	
    	} catch (EmptyResultDataAccessException e){
             throw new EntidadeNaoEncontradaExecption(
               String.format(MSG_RESTAURANTE_NAO_ENCONTRADA, id));	
    		
		 } catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
			  String.format(MSG_RESTAURANTE_EM_USO, id));
		}
    	
		
	}
     
	

}
