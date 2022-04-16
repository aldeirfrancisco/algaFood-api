package com.algaFood.algaFoodapi.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaFood.algaFoodapi.domain.exception.EntidadeEmUsoException;
import com.algaFood.algaFoodapi.domain.exception.FormaDePagamentoNaoEcontradaException;
import com.algaFood.algaFoodapi.domain.model.FormaPagamento;
import com.algaFood.algaFoodapi.domain.repository.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {
   
	@Autowired
	private FormaPagamentoRepository repository;
	
	 public static final String MSG_FORMA_PAGAMENTO_EM_USO = "Forma de pagamento de código %d não pode ser removida, pois está em uso.";
	
	    public List<FormaPagamento> listar() {
	        return repository.findAll();
	    }

	    @Transactional

	    public FormaPagamento salvar(FormaPagamento formaPagamento) {
	        return repository.save(formaPagamento);
	    }

	    @Transactional
	    public void excluir(Long id) {

	        try {

	            repository.deleteById(id);
	            repository.flush();

	        }catch (EmptyResultDataAccessException ex){

	            throw new FormaDePagamentoNaoEcontradaException(id);

	        }catch (DataIntegrityViolationException ex){

	            throw new EntidadeEmUsoException(String.format(MSG_FORMA_PAGAMENTO_EM_USO, id));

	        }

	    }


	    public FormaPagamento buscarOuFalhar(Long id){
	        return repository.findById(id)
	                .orElseThrow(() -> new FormaDePagamentoNaoEcontradaException(id));

	    }

}
