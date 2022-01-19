package com.algaFood.algaFoodapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.algaFood.algaFoodapi.modelo.Cliente;

@Component
public class AtivacaoClienteService {
	
	@Autowired
   private ApplicationEventPublisher eventPublisher;
 
	public void ativar(Cliente cliente) {
		cliente.ativo();
		eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
	}
}
