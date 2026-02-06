package com.gustavosouza.votacao.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PAUTA")
public class PautaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PAUTA")
    private Long idPauta;

    @Column(name = "ASSUNTO", unique = true)
    private String assunto;

    @Column(name = "VOTOS_NECESSARIOS")
    private Integer quantidadeDeVotosNecessarios;

    @Column(name = "DATA_INICIO")
    private LocalDate dataInicio;

    @Column(name = "DATA_ENCERRAMENTO")
    private LocalDate dataEncerramento;
}
