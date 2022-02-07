package com.algaFood.algaFoodapi.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaFood.algaFoodapi.domain.exception.EntidadeEmUsoException;
import com.algaFood.algaFoodapi.domain.exception.EntidadeNaoEncontradaExecption;
import com.algaFood.algaFoodapi.domain.model.Estado;
import com.algaFood.algaFoodapi.domain.model.Restaurante;
import com.algaFood.algaFoodapi.domain.model.Estado;
import com.algaFood.algaFoodapi.domain.model.Estado;
import com.algaFood.algaFoodapi.domain.repository.EstadoRepository;


@RestController
@RequestMapping("/estados")
public class EstadoController {
	
     @Autowired
	private EstadoRepository estadoRepository;


    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.listar();

    }
    
	 @GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscar(@PathVariable Long estadoId){
		 Estado estado  =  estadoRepository.buscar(estadoId);
	       return ResponseEntity.ok(estado);
	}
    
	 @PostMapping
	 public ResponseEntity<?> adicionar(@RequestBody Estado estado){ 
		 try {
			  estado = estadoRepository.adicionar(estado);
			 return ResponseEntity.status(HttpStatus.CREATED)
					 .body(estado);
		} catch (EntidadeNaoEncontradaExecption e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		 
	 }
	 
	 @PutMapping("/{estadoId}")
	    public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId,
	    		@RequestBody Estado estado) {
	    	Estado estadoAtual =  estadoRepository.buscar(estadoId);
	    	if(estadoAtual != null) {
	    		BeanUtils.copyProperties(estado, estadoAtual, "id");
	    		estadoAtual = estadoRepository.atualizar(estadoAtual);
	    		return ResponseEntity.ok(estadoAtual);
	    	}
	    	return ResponseEntity.notFound().build();
	    	
	    }
	 
	  @DeleteMapping("/{estadoId}")
	    public ResponseEntity<Restaurante> remover(@PathVariable Long estadoId) {
	    	try {
	    		estadoRepository.remover(estadoId);
	        		return ResponseEntity.noContent().build();
	        		
	    	} catch (EntidadeNaoEncontradaExecption e ){
	        		return ResponseEntity.notFound().build();
	        	
		    } catch (EntidadeEmUsoException e){
		    	
					return ResponseEntity.status(HttpStatus.CONFLICT).build();
				}
	    }

	    

}
