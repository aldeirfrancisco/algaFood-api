package com.algaFood.algaFoodapi.notificador;

import org.springframework.stereotype.Component;

import com.algaFood.algaFoodapi.modelo.Cliente;


@TipoDoNotificador(NivelDeUrgencia.NAO_URGENTE)
@Component
public class NotificadorSMS implements Notificador {
     

	
	
	@Override
	public void notificar(Cliente cliente, String msn) {
		System.out.printf("Notificando %s através do telefone: %s: %s\n", cliente.getNome(), cliente.getTelefone(), msn);
	}
	
	
	
}
