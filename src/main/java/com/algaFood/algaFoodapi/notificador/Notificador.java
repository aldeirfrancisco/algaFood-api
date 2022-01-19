package com.algaFood.algaFoodapi.notificador;

import com.algaFood.algaFoodapi.modelo.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String msn);

}