package com.algaFood.algaFoodapi.domain.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaFood.algaFoodapi.domain.exception.EntidadeEmUsoException;
import com.algaFood.algaFoodapi.domain.exception.EstadoNaoEncontradoException;
import com.algaFood.algaFoodapi.domain.model.Estado;
import com.algaFood.algaFoodapi.domain.repository.EstadoRepository;

@Service
public class EstadoService {
	
	
	    private static final String MSG_ESTADO_EM_USO 
	      = "Estado de código %d não pode ser removido, pois está em uso";
	   
		
		private EstadoRepository estadoRepository;
	
	    public EstadoService(EstadoRepository estadoRepository) {
	    	this.estadoRepository = estadoRepository;
	    }
	    public List<Estado> listar(){
	        return estadoRepository.findAll();
	
	    }


		public Estado buscar(Long estadoId) {
			return estadoRepository.findById(estadoId)
					.orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
			
		}
	
		@Transactional
		public Estado adicionar(Estado estado) {
			return estadoRepository.save(estado);	
		}
	
		@Transactional
		public Estado atualizar(Estado estado) {
			return estadoRepository.save(estado);
		}
	
		@Transactional
		public void remover(Long id) {
			
			try {
	        	estadoRepository.deleteById(id);
	        	estadoRepository.flush();
	        	
	    	} catch (EmptyResultDataAccessException e){
	    		
	             throw new EstadoNaoEncontradoException(e.getLocalizedMessage());	
	    		
			 } catch (DataIntegrityViolationException e) {
				 
				throw new EntidadeEmUsoException(
				  String.format(MSG_ESTADO_EM_USO, id));
			}
	    	
			
		}

   
   
}
