package com.algaFood.algaFoodapi.service;

import com.algaFood.algaFoodapi.modelo.Cliente;

public class ClienteAtivadoEvent {
	   private Cliente cliente;

	public ClienteAtivadoEvent(Cliente cliente) {
		super();
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}
	   

}
