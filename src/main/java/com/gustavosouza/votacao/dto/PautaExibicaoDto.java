package com.gustavosouza.votacao.dto;

import com.gustavosouza.votacao.model.PautaModel;

import java.time.LocalDate;

public record PautaExibicaoDto(
        String assunto,
        Integer quantidadeDeVotosNecassarios,
        LocalDate dataInicio,
        LocalDate dataEncerramento
) {

    public PautaExibicaoDto(PautaModel pauta){
        this(
                pauta.getAssunto(),
                pauta.getQuantidadeDeVotosNecassarios(),
                pauta.getDataInicio(),
                pauta.getDataEncerramento()
        );
    }

}
