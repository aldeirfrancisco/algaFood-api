package com.algaFood.algaFoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaFood.algaFoodapi.api.mapper.GrupoMapper;
import com.algaFood.algaFoodapi.api.model.dto.GrupoDTO;
import com.algaFood.algaFoodapi.api.model.input.grupo.GrupoInput;
import com.algaFood.algaFoodapi.domain.service.serviceInterface.GrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    private final GrupoService service;
    private final GrupoMapper mapper;

    public GrupoController(GrupoService service, GrupoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @GetMapping
    public List<GrupoDTO> listar() {

        return mapper.toCollectionDto(service.listar());

    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GrupoDTO buscar(@PathVariable Long id){

        return mapper.toDto(service.buscarOuFalhar(id));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoDTO adicionar(@RequestBody @Valid GrupoInput grupoInput){

        var grupo = mapper.toEntity(grupoInput);

        return mapper.toDto(service.salvar(grupo));

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GrupoDTO atualizar(@PathVariable Long id,
                              @RequestBody @Valid GrupoInput grupoInput){

        var grupo = service.buscarOuFalhar(id);

        mapper.copyToEntity(grupoInput, grupo);

        return mapper.toDto(service.salvar(grupo));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {

        service.excluir(id);

    }
}
