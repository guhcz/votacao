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
@Table(name = "USUARIO")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

}