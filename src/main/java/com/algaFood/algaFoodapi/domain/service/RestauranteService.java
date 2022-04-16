package com.algaFood.algaFoodapi.domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaFood.algaFoodapi.api.mapper.RestauranteMapper;
import com.algaFood.algaFoodapi.api.model.dto.RestauranteDTO;
import com.algaFood.algaFoodapi.api.model.input.restaurante.RestauranteInput;
import com.algaFood.algaFoodapi.domain.exception.EntidadeEmUsoException;
import com.algaFood.algaFoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.algaFood.algaFoodapi.domain.model.Restaurante;
import com.algaFood.algaFoodapi.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	 private static final String MSG_RESTAURANTE_EM_USO 
	       = "Restaurante de código %d não pode ser removido, pois está em uso";


	private static final String MSG_COZINHA_NAO_ENCONTRADA 
	       = "Não existe  cadastro de cozinha com código %d";
	 
	 @Autowired
	 private CozinhaService cozinhaService;
	 
	 private RestauranteRepository restauranteRepository;
	 
	 private final RestauranteMapper mapper;
	 
	 public RestauranteService(RestauranteRepository restauranteRepository, RestauranteMapper mapper){
		 this.restauranteRepository = restauranteRepository;
		 this.mapper = mapper;
	 }
	 
	 

	public List<RestauranteDTO> listar() {
		 return mapper.toCollectionDto(restauranteRepository.findAll());
	}
	
	public List<RestauranteDTO> restaurantePorNomeFrete(String nome, BigDecimal taxaFreteInicial, BigDecimal         					taxaFreteFinal) {
		 var restaurantes = restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
		 return mapper.toCollectionDto(restaurantes);
	}


	public Restaurante buscar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(()-> new  RestauranteNaoEncontradoException(restauranteId));
		
		
			
		
	}
	
	public RestauranteDTO buscarDTO(Long restauranteId) {
		var restaurante = buscar(restauranteId);
		return mapper.toDto(restaurante);
		
			
		
	}

	@Transactional
	public RestauranteDTO adicionar(RestauranteInput restauranteInput) {
		 var restaurante = mapper.toEntity(restauranteInput);
		Long idCozinha = restaurante.getCozinha().getId();
		var cozinha = cozinhaService.buscar(idCozinha);
		 restaurante.setCozinha( cozinha );
		 return  mapper.toDto(restauranteRepository.save(restaurante));
 
	}
    
	@Transactional
	public RestauranteDTO atualizar(Long id, RestauranteInput restauranteInput) {
		var restaurante = buscar(id);
		mapper.copyToEntity(restauranteInput, restaurante);
		
		Long idCozinha = restaurante.getCozinha().getId();
		var cozinha = cozinhaService.buscar(idCozinha);
		 restaurante.setCozinha(cozinha);
		var rest = restauranteRepository.save(restaurante);

		 
		  return buscarDTO(rest.getId());
	
		 
		 
	}
    
	@Transactional
	public void remover(Long id) {
		
		try {
			
        	restauranteRepository.deleteById(id);
        	restauranteRepository.flush();
        	
    	} catch (EmptyResultDataAccessException e){
             throw new RestauranteNaoEncontradoException(id);	
    		
		 } catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
			  String.format(MSG_RESTAURANTE_EM_USO, id));
		}
    	
		
	}
     
	

}
