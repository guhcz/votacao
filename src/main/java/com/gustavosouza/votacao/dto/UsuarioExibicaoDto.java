package com.gustavosouza.votacao.dto;

import com.gustavosouza.votacao.model.UsuarioModel;

import java.time.LocalDate;


public record UsuarioExibicaoDto(
        Long id,
        String nome,
        String email,
        LocalDate dataNascimento
) {
    public UsuarioExibicaoDto(UsuarioModel usuario) {
        this(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataNascimento()
        );
    }

}
