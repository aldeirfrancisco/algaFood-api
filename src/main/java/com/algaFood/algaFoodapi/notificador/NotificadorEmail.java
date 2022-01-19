package com.algaFood.algaFoodapi.notificador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaFood.algaFoodapi.modelo.Cliente;

@TipoDoNotificador(NivelDeUrgencia.URGENTE)
@Component
public class NotificadorEmail implements Notificador {
	
	@Autowired
	private NotificadorProperties properties;

	@Override
	public void notificar(Cliente cliente, String msn) {
		System.out.printf("hostServidor "+properties.getHostServidor());
		System.out.printf("portaServidor "+ properties.getPortaServidor() );
		
		System.out.printf("Notificado %s através de e-mail %s: %s\n  %s\\n", cliente.getNome(), cliente.getEmail(), msn  );
	}
	
	
}
