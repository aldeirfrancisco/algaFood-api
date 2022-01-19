package com.algaFood.algaFoodapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algaFood.algaFoodapi.modelo.Cliente;
import com.algaFood.algaFoodapi.service.AtivacaoClienteService;

@Controller
public class MeuPrimeiroController {
      private AtivacaoClienteService ativacaoClienteService;
      public  MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService){
    	  this.ativacaoClienteService = ativacaoClienteService;
      }
      
	@GetMapping("/hello")
	@ResponseBody
	 public String hellor() {
		Cliente joao = new Cliente("Joao", "joao@gmail.com","12343234");
		this.ativacaoClienteService.ativar(joao);
		 return "Hello";
	 }
}
