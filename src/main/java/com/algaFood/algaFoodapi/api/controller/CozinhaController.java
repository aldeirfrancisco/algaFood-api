package com.algaFood.algaFoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaFood.algaFoodapi.api.mapper.CozinhaMapper;
import com.algaFood.algaFoodapi.api.model.dto.CozinhaDTO;
import com.algaFood.algaFoodapi.api.model.input.cozinha.CozinhaInput;
import com.algaFood.algaFoodapi.domain.model.Cozinha;
import com.algaFood.algaFoodapi.domain.service.CozinhaService;


@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
     @Autowired
	private CozinhaService cozinhaService;
     
     @Autowired
     private CozinhaMapper mapper;

     
    @GetMapping
    public List<CozinhaDTO> listarCozinha(){
        return cozinhaService.listar();

    }

    @GetMapping("/{cozinhaId}")
    public CozinhaDTO buscar(@PathVariable Long cozinhaId){
    	return cozinhaService.buscarDTO(cozinhaId);
     
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@Valid @RequestBody Cozinha cozinha){

        return cozinhaService.adicionar(cozinha);

    }

    @PutMapping("/{cozinhaId}")
    public CozinhaDTO atualizar(@PathVariable Long cozinhaId,
    		@Valid @RequestBody CozinhaInput cozinhaInput) {

    	var cozinhaAtual =  cozinhaService.buscar(cozinhaId);
    	mapper.copyToEntity(cozinhaInput, cozinhaAtual);
    	
        return cozinhaService.atualizar(cozinhaAtual); 

    }
    
    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        		cozinhaService.remover(cozinhaId);
    }


}
