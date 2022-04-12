package com.algaFood.algaFoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import com.algaFood.algaFoodapi.api.mapper.CidadeMapper;
import com.algaFood.algaFoodapi.api.model.dto.CidadeDTO;
import com.algaFood.algaFoodapi.api.model.input.cidade.CidadeInput;
import com.algaFood.algaFoodapi.domain.exception.EstadoNaoEncontradoException;
import com.algaFood.algaFoodapi.domain.exception.NegocioException;
import com.algaFood.algaFoodapi.domain.model.Cidade;
import com.algaFood.algaFoodapi.domain.service.CidadeService;


@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
//     @Autowired
//	private CidadeService cidadeService;
	 private final CidadeService cidadeService;
	 
	 private final CidadeMapper mapper;
	 
    CidadeController(CidadeService cidadeService, CidadeMapper mapper){
    	this.cidadeService = cidadeService;
    	this.mapper = mapper;
    }

    @GetMapping
    public List<CidadeDTO> listar(){
        return mapper.toCollectionDto(cidadeService.listar());

    }
    
	 @GetMapping("/{cidadeId}")
	public CidadeDTO buscar(@PathVariable Long cidadeId){
	       return mapper.toDto(cidadeService.buscar(cidadeId));
	}
    
	 @PostMapping
	 @ResponseStatus(HttpStatus.CREATED)
	 public CidadeDTO adicionar(@RequestBody @Valid CidadeInput cidadeInput){ 
			try {
				  var cidade = mapper.toEntity(cidadeInput);
				return mapper.toDto(cidadeService.adicionar(cidade));
				
            } catch (EstadoNaoEncontradoException e) {
				
                throw new NegocioException(e.getMessage(), e);
			}
			 
	 }
	 
	 @PutMapping("/{cidadeId}")
	 public CidadeDTO atualizar(@PathVariable Long cidadeId,
             @RequestBody @Valid CidadeInput cidadeInput) {
		 
	    	var cidadeAtual =  cidadeService.buscar(cidadeId);
	    	mapper.copyToEntity(cidadeInput, cidadeAtual);
	    	try {
	    		
	    		return mapper.toDto(cidadeService.atualizar(cidadeAtual));
	    		
			} catch (EstadoNaoEncontradoException e) {
				
				throw new NegocioException(e.getMessage(), e);
			}
	    	
	    }
	 
	  @DeleteMapping("/{cidadeId}")
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  public void remover(@PathVariable Long cidadeId) {
	    		cidadeService.remover(cidadeId);

	    }

}
