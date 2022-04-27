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

import com.algaFood.algaFoodapi.api.mapper.PermissaoMapper;
import com.algaFood.algaFoodapi.api.model.dto.PermissaoDTO;
import com.algaFood.algaFoodapi.domain.service.serviceInterface.GrupoService;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissoesController {

    private final GrupoService grupoService;
    private final PermissaoMapper permissaoMapper;


    public GrupoPermissoesController(GrupoService grupoService, PermissaoMapper permissaoMapper) {
        this.grupoService = grupoService;
        this.permissaoMapper = permissaoMapper;
    }

    @GetMapping
    public List<PermissaoDTO> listar(@PathVariable Long grupoId){

        var grupo = grupoService.buscarOuFalhar(grupoId);

        var permissoes = grupo.getPermissoes();

        return permissaoMapper.toCollectionDto(permissoes);

    }


    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId){

        grupoService.associaPermissao(grupoId, permissaoId);

    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId){

        grupoService.desassociaPermissao(grupoId, permissaoId);

    }


}
