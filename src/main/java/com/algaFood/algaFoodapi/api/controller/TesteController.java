package com.algaFood.algaFoodapi.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaFood.algaFoodapi.domain.model.Restaurante;
import com.algaFood.algaFoodapi.domain.repository.RestauranteRepository;
import com.algaFood.algaFoodapi.domain.service.RestauranteService;
import com.algaFood.algaFoodapi.infrastrutura.repository.spec.RestauranteComFreteGratisSpec;
import com.algaFood.algaFoodapi.infrastrutura.repository.spec.RestauranteComNomeSemelhanteSpec;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private RestauranteRepository restauranteRepository;

	 
	 @GetMapping("/restaurantes/por_nome_frete")
	public  List<Restaurante> restaurantePorNomeFrete(String nome,
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
	       return restauranteService.restaurantePorNomeFrete(nome, taxaFreteInicial, taxaFreteFinal);
	}
	 
	 @GetMapping("/restaurantes/com_frete_gratis")
		public  List<Restaurante> restauranteComFreteGratis(String nome){
		       var comFreteGratis = new RestauranteComFreteGratisSpec();
		       var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);
		       return restauranteRepository.findAll(comFreteGratis.and(comNomeSemelhante));
		}
}
	
