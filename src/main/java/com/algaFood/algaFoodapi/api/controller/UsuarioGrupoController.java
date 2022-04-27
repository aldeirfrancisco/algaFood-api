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

import com.algaFood.algaFoodapi.api.mapper.GrupoMapper;
import com.algaFood.algaFoodapi.api.model.dto.GrupoDTO;
import com.algaFood.algaFoodapi.domain.service.serviceInterface.UsuarioService;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    private final UsuarioService usuarioService;
    private final GrupoMapper grupoMapper;

    public UsuarioGrupoController(UsuarioService usuarioService, GrupoMapper grupoMapper) {
        this.usuarioService = usuarioService;
        this.grupoMapper = grupoMapper;
    }

    @GetMapping
    public List<GrupoDTO> listar(@PathVariable Long usuarioId){

        var usuario = usuarioService.buscarOuFalhar(usuarioId);

        var grupos = usuario.getGrupos();

        return grupoMapper.toCollectionDto(grupos);

    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associaGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId){

        usuarioService.associaGrupo(usuarioId, grupoId);

    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociaGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId){

        usuarioService.desassociaGrupo(usuarioId, grupoId);
    }

}
