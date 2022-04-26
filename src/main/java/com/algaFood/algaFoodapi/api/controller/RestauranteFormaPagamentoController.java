package com.algaFood.algaFoodapi.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaFood.algaFoodapi.api.mapper.FormaPagamentoMapper;
import com.algaFood.algaFoodapi.api.model.dto.FormaPagamentoDTO;
import com.algaFood.algaFoodapi.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

     private final RestauranteService restauranteService;
     private final FormaPagamentoMapper formaPagamentoMapper;

    public RestauranteFormaPagamentoController(RestauranteService restauranteService, FormaPagamentoMapper formaPagamentoMapper) {
        this.restauranteService = restauranteService;
        this.formaPagamentoMapper = formaPagamentoMapper;
    }

    @GetMapping
    public List<FormaPagamentoDTO> listar(@PathVariable Long restauranteId){

        var restaurante = restauranteService.buscar(restauranteId);
        return formaPagamentoMapper.toCollectionDto(restaurante.getFormasPagamento());

    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){

        restauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);

    }

    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){

        restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);

    }

}
