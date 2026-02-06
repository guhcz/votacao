package com.gustavosouza.votacao.repository;

import com.gustavosouza.votacao.model.UsuarioModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

    List<UsuarioModel> findByDataNascimentoBetween(LocalDate dataInicial, LocalDate dataFinal);


}
