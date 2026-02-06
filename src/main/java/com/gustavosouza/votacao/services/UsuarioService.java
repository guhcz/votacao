package com.gustavosouza.votacao.services;

import com.gustavosouza.votacao.dto.UsuarioCadastroDto;
import com.gustavosouza.votacao.dto.UsuarioExibicaoDto;
import com.gustavosouza.votacao.model.UsuarioModel;
import com.gustavosouza.votacao.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDto salvarUsuario(UsuarioCadastroDto usuarioDto) {
        UsuarioModel usuario = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuario);
        return new UsuarioExibicaoDto(usuarioRepository.save(usuario));
    }

    public UsuarioExibicaoDto atualizarUsuarioPorId(Long id, UsuarioModel usuario) {
        UsuarioModel usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao encontrado!"));
        UsuarioModel usuarioAtualizado = UsuarioModel.builder()
                .email(usuario.getEmail() != null ? usuario.getEmail() :
                        usuarioEntity.getEmail())
                .nome(usuario.getNome() != null ? usuario.getNome() :
                        usuarioEntity.getNome())
                .senha(usuario.getSenha() != null ? usuario.getSenha() :
                        usuarioEntity.getSenha())
                .dataNascimento(usuario.getDataNascimento() != null ? usuario.getDataNascimento() :
                        usuarioEntity.getDataNascimento())
                .idUsuario(usuarioEntity.getIdUsuario())
                .build();
        return new UsuarioExibicaoDto(usuarioRepository.save(usuarioAtualizado));
    }

    public void deletarUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    public List<UsuarioExibicaoDto> buscarTodosUsuarios() {
        return usuarioRepository
                .findAll()
                .stream()
                .map(UsuarioExibicaoDto::new)
                .toList();
    }

    public UsuarioModel buscarPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).orElseThrow(
                () -> new RuntimeException("Id do usuario nao encontrado!")
        );
    }

    public UsuarioExibicaoDto buscarUsuarioPorEmail(String email) {
        return new UsuarioExibicaoDto(usuarioRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("E-mail nao encontrado!")
        ));
    }

    public List<UsuarioExibicaoDto> filtrarPelaDataNascimento(LocalDate dataInicial, LocalDate dataFinal) {
        List<UsuarioModel> usuario = usuarioRepository.findByDataNascimentoBetween(dataInicial, dataFinal);
        if (usuario.isEmpty()) {
            throw new RuntimeException("Nenhuma data encontrada de acordo com o filtro!");
        }
        return usuario
                .stream()
                .map(UsuarioExibicaoDto::new)
                .toList();
    }



}
