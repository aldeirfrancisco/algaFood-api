package com.algaFood.algaFoodapi.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaFood.algaFoodapi.notificador.NivelDeUrgencia;
import com.algaFood.algaFoodapi.notificador.Notificador;
import com.algaFood.algaFoodapi.notificador.TipoDoNotificador;
import com.algaFood.algaFoodapi.service.ClienteAtivadoEvent;

@Component
public class NotificacaoService {
	@TipoDoNotificador(NivelDeUrgencia.NAO_URGENTE)
	@Autowired
	private Notificador  notificador;
	
	@EventListener
	public void clienteAtivoListener(ClienteAtivadoEvent event) {
		notificador.notificar(event.getCliente(), "Seu cadastro no sistema está ativo!");
	}
}
