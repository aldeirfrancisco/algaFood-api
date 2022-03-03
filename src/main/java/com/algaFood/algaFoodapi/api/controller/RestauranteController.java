package com.algaFood.algaFoodapi.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaFood.algaFoodapi.domain.exception.EntidadeEmUsoException;
import com.algaFood.algaFoodapi.domain.exception.EntidadeNaoEncontradaExecption;
import com.algaFood.algaFoodapi.domain.model.Restaurante;
import com.algaFood.algaFoodapi.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId){
		 Restaurante restaurante  =  restauranteService.buscar(restauranteId);
	       return ResponseEntity.ok(restaurante);
	}
	 @PostMapping
	 public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){ 
		 try {
			  restaurante = restauranteService.adicionar(restaurante);
			 return ResponseEntity.status(HttpStatus.CREATED)
					 .body(restaurante);
		} catch (EntidadeNaoEncontradaExecption e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		 
	 }
	 
	    @PutMapping("/{restauranteId}")
	    public ResponseEntity<Restaurante> atualizar(@PathVariable Long restauranteId,
	    		@RequestBody Restaurante restaurante) {
	    	Restaurante restauranteAtual =  restauranteService.buscar(restauranteId);
	    	if(restauranteAtual != null) {
	    		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco");
	    		restauranteAtual = restauranteService.atualizar(restauranteAtual);
	    		return ResponseEntity.ok(restauranteAtual);
	    	}
	    	return ResponseEntity.notFound().build();
	    	
	    }
	    
	    @DeleteMapping("/{restauranteId}")
	    public ResponseEntity<Restaurante> remover(@PathVariable Long restauranteId) {
	    	try {
	    		restauranteService.remover(restauranteId);
	        		return ResponseEntity.noContent().build();
	        		
	    	} catch (EntidadeNaoEncontradaExecption e ){
	        		return ResponseEntity.notFound().build();
	        	
		    } catch (EntidadeEmUsoException e){
		    	
					return ResponseEntity.status(HttpStatus.CONFLICT).build();
				}
	    }

}
