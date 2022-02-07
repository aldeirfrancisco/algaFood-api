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
import com.algaFood.algaFoodapi.domain.model.Cidade;
import com.algaFood.algaFoodapi.domain.model.Restaurante;
import com.algaFood.algaFoodapi.domain.repository.CidadeRepository;


@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
     @Autowired
	private CidadeRepository cidadeRepository;


    @GetMapping
    public List<Cidade> listar(){
        return cidadeRepository.listar();

    }
    
	 @GetMapping("/{cidadeId}")
	public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId){
		 Cidade cidade  =  cidadeRepository.buscar(cidadeId);
	       return ResponseEntity.ok(cidade);
	}
    
	 @PostMapping
	 public ResponseEntity<?> adicionar(@RequestBody Cidade cidade){ 
		 try {
			  cidade = cidadeRepository.adicionar(cidade);
			 return ResponseEntity.status(HttpStatus.CREATED)
					 .body(cidade);
		} catch (EntidadeNaoEncontradaExecption e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		 
	 }
	 
	 @PutMapping("/{cidadeId}")
	    public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId,
	    		@RequestBody Cidade cidade) {
	    	Cidade cidadeAtual =  cidadeRepository.buscar(cidadeId);
	    	if(cidadeAtual != null) {
	    		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
	    		cidadeAtual = cidadeRepository.atualizar(cidadeAtual);
	    		return ResponseEntity.ok(cidadeAtual);
	    	}
	    	return ResponseEntity.notFound().build();
	    	
	    }
	 
	  @DeleteMapping("/{cidadeId}")
	    public ResponseEntity<Restaurante> remover(@PathVariable Long cidadeId) {
	    	try {
	    		cidadeRepository.remover(cidadeId);
	        		return ResponseEntity.noContent().build();
	        		
	    	} catch (EntidadeNaoEncontradaExecption e ){
	        		return ResponseEntity.notFound().build();
	        	
		    } catch (EntidadeEmUsoException e){
		    	
					return ResponseEntity.status(HttpStatus.CONFLICT).build();
				}
	    }

	    

}