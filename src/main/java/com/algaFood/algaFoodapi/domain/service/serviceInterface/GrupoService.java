package com.algaFood.algaFoodapi.domain.service.serviceInterface;

import java.util.List;

import com.algaFood.algaFoodapi.domain.model.Grupo;

public interface GrupoService {

  
    List<Grupo> listar();


    Grupo buscarOuFalhar(Long id);

  
    Grupo salvar(Grupo grupo);

    
    void excluir(Long id);

   
    void associaPermissao(Long grupoId, Long permissaoId);

    
    void desassociaPermissao(Long grupoId, Long permissaoId);

}
