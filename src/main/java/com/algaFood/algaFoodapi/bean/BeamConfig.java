package com.algaFood.algaFoodapi.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaFood.algaFoodapi.notificador.NotificadorEmail;
import com.algaFood.algaFoodapi.service.AtivacaoClienteService;

//@Configuration
public class BeamConfig {
	
	@Bean
	public NotificadorEmail notificadorEmail() {
		NotificadorEmail notificadorEmail = new NotificadorEmail();
		return notificadorEmail;
	}
//	@Bean
//	public AtivacaoClienteService ativacaoClienteService() {
//		return new AtivacaoClienteService(notificadorEmail());
//	}
}
