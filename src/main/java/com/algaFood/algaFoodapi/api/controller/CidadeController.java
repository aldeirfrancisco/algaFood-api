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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaFood.algaFoodapi.domain.exception.EntidadeEmUsoException;
import com.algaFood.algaFoodapi.domain.exception.EntidadeNaoEncontradaExecption;
import com.algaFood.algaFoodapi.domain.model.Cidade;
import com.algaFood.algaFoodapi.domain.model.Restaurante;
import com.algaFood.algaFoodapi.domain.repository.CidadeRepository;
import com.algaFood.algaFoodapi.domain.service.CidadeService;


@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
//     @Autowired
//	private CidadeService cidadeService;
	 private final CidadeService cidadeService;
	 
    CidadeController(CidadeService cidadeService){
    	this.cidadeService = cidadeService;
    }

    @GetMapping
    public List<Cidade> listar(){
        return this.cidadeService.listar();

    }
    
	 @GetMapping("/{cidadeId}")
	public Cidade buscar(@PathVariable Long cidadeId){

	       return  cidadeService.buscar(cidadeId);
	}
    
	 @PostMapping
	 @ResponseStatus(HttpStatus.CREATED)
	 public Cidade adicionar(@RequestBody Cidade cidade){ 
			return cidadeService.adicionar(cidade);
			 
	 }
	 
	 @PutMapping("/{cidadeId}")
	    public Cidade atualizar(@PathVariable Long cidadeId,
	    		@RequestBody Cidade cidade) {
		 
	    	Cidade cidadeAtual =  cidadeService.buscar(cidadeId);
	    	BeanUtils.copyProperties(cidade, cidadeAtual, "id");
	    	
	    	return cidadeService.atualizar(cidadeAtual);
	    	
	    }
	 
	  @DeleteMapping("/{cidadeId}")
	    public void remover(@PathVariable Long cidadeId) {
	    		cidadeService.remover(cidadeId);

	    }

	    

}
