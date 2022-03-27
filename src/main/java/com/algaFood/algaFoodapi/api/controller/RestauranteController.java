package com.algaFood.algaFoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaFood.algaFoodapi.Grupos;
import com.algaFood.algaFoodapi.domain.exception.NegocioException;
import com.algaFood.algaFoodapi.domain.model.Restaurante;
import com.algaFood.algaFoodapi.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteService restauranteService;
	
	 @GetMapping
	public List<Restaurante> listarRestaurante(){
		return restauranteService.listar();
	}
	 
	 @GetMapping("/{restauranteId}")
	public Restaurante buscar(@PathVariable Long restauranteId){
		 return restauranteService.buscar(restauranteId);
	    
	}
	 @PostMapping
	 @ResponseStatus(HttpStatus.CREATED)
	 public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante){ 
		 try {
			 return  restauranteService.adicionar(restaurante);
		 } catch (Exception e) {
				
             throw new NegocioException(e.getMessage());
			}
	 }
	 
	 
	    @PutMapping("/{restauranteId}")
	    public Restaurante atualizar(@PathVariable Long restauranteId,
	    		@RequestBody @Valid Restaurante restaurante) {
	    	
	    	 Restaurante restauranteAtual =  restauranteService.buscar(restauranteId);
	    	 BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco");
	    	 
	    	 try {
	    		 
	    		 return restauranteService.atualizar(restauranteAtual);
	    	 
		    } catch (Exception e) {
				
	            throw new NegocioException(e.getMessage());
			}
	    	
	    }
	    
	    
	    @DeleteMapping("/{restauranteId}")
	    public  void remover(@PathVariable Long restauranteId) {
	    		restauranteService.remover(restauranteId);
	    }

}
