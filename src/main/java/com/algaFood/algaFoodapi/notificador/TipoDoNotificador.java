package com.algaFood.algaFoodapi.notificador;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import org.springframework.beans.factory.annotation.Qualifier;

@Retention(RUNTIME)
@Qualifier
public @interface TipoDoNotificador {
         
	NivelDeUrgencia value();
}
