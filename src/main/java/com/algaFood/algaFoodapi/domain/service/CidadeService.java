package com.algaFood.algaFoodapi.domain.service;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaFood.algaFoodapi.domain.exception.EntidadeEmUsoException;
import com.algaFood.algaFoodapi.domain.exception.EntidadeNaoEncontradaExecption;
import com.algaFood.algaFoodapi.domain.model.Cidade;
import com.algaFood.algaFoodapi.domain.repository.CidadeRepository;

@Service
public class CidadeService {
	
	public static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";
	
	
   private final CidadeRepository cidadeRepository;
	
	CidadeService(CidadeRepository cidadeRepository){
		this.cidadeRepository = cidadeRepository;
	}
    
    public List<Cidade> listar(){
        return cidadeRepository.findAll();

    }

    
	public Cidade buscar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId)
				.orElseThrow(()-> new EmptyResultDataAccessException(1));
	
		 
	}

	@Transactional
	public Cidade adicionar(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}

	@Transactional
	public Cidade atualizar(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}

	@Transactional
	public void remover(Long id) {
		
		try {
        	cidadeRepository.deleteById(id);
        	
    	} catch (EmptyResultDataAccessException e){
             throw new EntidadeNaoEncontradaExecption(
               String.format("Não existe um cadastro de cidade com código %d", id));	
    		
		 } catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
			  String.format( MSG_COZINHA_EM_USO, id));
		}
    	
		
	}

   
   
}
