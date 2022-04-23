package com.algaFood.algaFoodapi.domain.service.serviceInterface;

import java.util.List;

import com.algaFood.algaFoodapi.domain.model.Usuario;

public interface UsuarioService {


    List<Usuario> listar();


    Usuario buscarOuFalhar(Long id);


    Usuario salvar(Usuario usuario);


    void atualizarSenha(Long id, String senhaAtual, String novaSenha);


    void associaGrupo(Long usuarioId, Long grupoId);


    void desassociaGrupo(Long usuarioId, Long grupoId);

}
