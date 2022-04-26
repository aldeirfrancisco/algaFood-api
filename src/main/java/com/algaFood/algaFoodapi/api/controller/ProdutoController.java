package com.algaFood.algaFoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaFood.algaFoodapi.api.mapper.ProdutoMapper;
import com.algaFood.algaFoodapi.api.model.dto.ProdutoDTO;
import com.algaFood.algaFoodapi.api.model.input.produto.ProdutoInput;
import com.algaFood.algaFoodapi.domain.repository.ProdutoRepository;
import com.algaFood.algaFoodapi.domain.service.RestauranteService;
import com.algaFood.algaFoodapi.domain.service.serviceInterface.ProdutoService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper mapper;
    private final RestauranteService restauranteService;


    public ProdutoController(ProdutoService produtoService, ProdutoMapper mapper, RestauranteService restauranteService, ProdutoRepository produtoRepository) {
        this.produtoService = produtoService;
        this.mapper = mapper;
        this.restauranteService = restauranteService;
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public List<ProdutoDTO> listar(@PathVariable Long restauranteId){

        var restaurante = restauranteService.buscar(restauranteId);

        var todosProdutos = produtoRepository.findByRestaurante(restaurante);

        return mapper.toCollectionDto(todosProdutos);

    }

    @GetMapping("/{produtoId}")
    public ProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {

        var produto = produtoService.buscarOuFalhar(restauranteId, produtoId);

        return mapper.toDto(produto);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput){

        var restaurante = restauranteService.buscar(restauranteId);

        var produto = mapper.toEntity(produtoInput);

        produto.setRestaurante(restaurante);

        produtoService.salvar(produto);

        return mapper.toDto(produto);

    }

    @PutMapping("/{produtoId}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDTO atualizar(@PathVariable Long restauranteId,
                                @PathVariable Long produtoId,
                                @RequestBody @Valid ProdutoInput produtoInput){

        restauranteService.buscar(restauranteId);

        var produto = produtoService.buscarOuFalhar(restauranteId, produtoId);

        mapper.copyToEntity(produtoInput, produto);

        produtoService.salvar(produto);

        return mapper.toDto(produto);


    }




}
