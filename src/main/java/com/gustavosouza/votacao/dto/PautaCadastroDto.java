package com.gustavosouza.votacao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PautaCadastroDto(
        @NotBlank(message = "O campo assunto precisa ser preenchido!")
        String assunto,

        @NotNull(message = "Defini a quantidade de votos necessários!")
        Integer quantidadeDeVotosNecassarios,

        @NotNull(message = "A data de inicio precisa ser preenchida!")
        LocalDate dataInicio,

        @NotNull(message = "A data de encerramento precisa ser preenchida!")
        LocalDate dataEncerramento
) {
}
