package com.algaFood.algaFoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaFood.algaFoodapi.domain.model.Estado;
import com.algaFood.algaFoodapi.domain.service.EstadoService;


@RestController
@RequestMapping("/estados")
public class EstadoController {
     @Autowired
	private EstadoService estadoService;


    @GetMapping
    public List<Estado> listar(){

        return estadoService.listar();

    }

//    @GetMapping("/{cozinhaId}")
//    public Estado buscar(@PathVariable Long cozinhaId){
//
//        return null;
//
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Estado adicionar(){
//
//        return null;
//
//    }
//
//    @PutMapping("/{cozinhaId}")
//    public Estado atualizar() {
//
//        return null;
//
//    }
//
//    @DeleteMapping("/{cozinhaId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void remover() {
//
//    }
}
