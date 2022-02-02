package com.algaFood.algaFoodapi.api.controller;

import java.util.List;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaFood.algaFoodapi.domain.model.Cozinha;
import com.algaFood.algaFoodapi.domain.service.CozinhaService;


@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
     @Autowired
	private CozinhaService cozinhaService;


    @GetMapping
    public List<Cozinha> listar(){
        return cozinhaService.listar();

    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId){
    	Cozinha coz =  cozinhaService.buscar(cozinhaId);
       return ResponseEntity.ok(coz);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha){

        return cozinhaService.adicionar(cozinha);

    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId,
    		@RequestBody Cozinha cozinha) {
    	Cozinha coz =  cozinhaService.buscar(cozinhaId);
    	if(coz != null) {
    	Cozinha cozinhaAtual = cozinhaService.atualizar(coz);
    		return ResponseEntity.ok(cozinhaAtual);
    	}
    	return ResponseEntity.notFound().build();
    	

    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {
    	try {
    	   	Cozinha coz =  cozinhaService.buscar(cozinhaId);
    	   	
        	if(coz != null) {
        		cozinhaService.remover(coz);
        		return ResponseEntity.noContent().build();
        	} else {
        		
        		return ResponseEntity.notFound().build();
        	}
      
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
    }
}
