package com.algaFood.algaFoodapi.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaFood.algaFoodapi.domain.model.Restaurante;
import com.algaFood.algaFoodapi.domain.service.RestauranteService;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	private RestauranteService restauranteService;
	

	 
	 @GetMapping("/restaurantes/por_nome_frete")
	public  List<Restaurante> restaurantePorNomeFrete(String nome,
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
	       return restauranteService.restaurantePorNomeFrete(nome, taxaFreteInicial, taxaFreteFinal);
	}
}
	
