package com.algaFood.algaFoodapi.domain.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaFood.algaFoodapi.domain.exception.CidadeNaoEncontradoException;
import com.algaFood.algaFoodapi.domain.exception.EntidadeEmUsoException;
import com.algaFood.algaFoodapi.domain.model.Cidade;
import com.algaFood.algaFoodapi.domain.model.Estado;
import com.algaFood.algaFoodapi.domain.repository.CidadeRepository;

@Service
public class CidadeService {
	
	private static final String MSG_CIDADE_EM_USO 
	     = "Cozinha de código %d não pode ser removida, pois está em uso";
	
	
   private final CidadeRepository cidadeRepository;
   
   private final EstadoService estadoService;
	
	CidadeService(CidadeRepository cidadeRepository , EstadoService estadoService){
		this.cidadeRepository = cidadeRepository;
		this.estadoService = estadoService;
	}
    
    public List<Cidade> listar(){
        return cidadeRepository.findAll();

    }

    
	public Cidade buscar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId)
				.orElseThrow(()-> new CidadeNaoEncontradoException(cidadeId));
	
	}

	@Transactional
	public Cidade adicionar(Cidade cidade) {
		Estado estado = estadoService.buscar(cidade.getEstado().getId());
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}


	public Cidade atualizar(Cidade cidade) {
		return adicionar(cidade);
	}

	@Transactional
	public void remover(Long id) {
		
		try {
        	cidadeRepository.deleteById(id);
        	cidadeRepository.flush();
    	} catch (EmptyResultDataAccessException e){
             throw new CidadeNaoEncontradoException(id);	
    		
		 } catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
			  String.format( MSG_CIDADE_EM_USO, id));
		}
    	
		
	}

   
   
}
