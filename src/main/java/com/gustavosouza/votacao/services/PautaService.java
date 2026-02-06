package com.gustavosouza.votacao.services;

import com.gustavosouza.votacao.dto.PautaCadastroDto;
import com.gustavosouza.votacao.dto.PautaExibicaoDto;
import com.gustavosouza.votacao.model.PautaModel;
import com.gustavosouza.votacao.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PautaService {

    private final PautaRepository repository;

    public PautaExibicaoDto criarPauta(PautaCadastroDto pautaDto) {
        PautaModel pauta = new PautaModel();
        BeanUtils.copyProperties(pautaDto, pauta);
        return new PautaExibicaoDto(repository.save(pauta));
    }

    public PautaExibicaoDto atualizarPauta(Long idPauta, PautaModel pauta) {
        PautaModel pautaEntity = repository.findById(idPauta).orElseThrow(() -> new RuntimeException("Pauta nao encontrada!"));
        PautaModel pautaAtualizada = PautaModel.builder()
                .assunto(pauta.getAssunto() != null ? pauta.getAssunto() :
                        pautaEntity.getAssunto())
                .quantidadeDeVotosNecassarios(pauta.getQuantidadeDeVotosNecassarios() != null ? pauta.getQuantidadeDeVotosNecassarios() :
                        pautaEntity.getQuantidadeDeVotosNecassarios())
                .dataInicio(pauta.getDataInicio() != null ? pauta.getDataInicio() :
                        pautaEntity.getDataInicio())
                .dataEncerramento(pauta.getDataEncerramento() != null ? pauta.getDataEncerramento() :
                        pautaEntity.getDataEncerramento())
                .idPauta(pautaEntity.getIdPauta())
                .build();

        return new PautaExibicaoDto(repository.save(pautaAtualizada));
    }

    public void excluirPauta(Long idPauta) {
        repository.deleteById(idPauta);
    }

    public void excluirPautaPeloAssunto(String assunto) {
        repository.deletarByAssunto(assunto);
    }

    public List<PautaExibicaoDto> buscarTodasPautas() {
        return repository.findAll()
                .stream()
                .map(PautaExibicaoDto::new)
                .toList();
    }

    public PautaModel buscarPautaPeloId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Pauta nao encontrada!"));
    }

    public PautaExibicaoDto buscarPautaPeloAssunto(String assunto) {
        return new PautaExibicaoDto(repository.findByAssunto(assunto)
                .orElseThrow(() -> new RuntimeException("Pauta nao encontrada!")
                ));
    }

    public List<PautaExibicaoDto> buscarPautaPeloNumeroDeVotos(Integer quantidadeDeVotosNecassarios) {
        List<PautaModel> pauta = repository.findByNumeroDeVotos(quantidadeDeVotosNecassarios);

        if (pauta.isEmpty()) {
            throw new RuntimeException("Pauta nao encontrada!");
        }
        return pauta
                .stream()
                .map(PautaExibicaoDto::new)
                .toList();
    }

    public List<PautaExibicaoDto> buscarPelaDataInicio(LocalDate primeiraData, LocalDate segundaData) {
        List<PautaModel> pauta = repository.findByDataInicioBetween(primeiraData, segundaData);

        if (pauta.isEmpty()) {
            throw new RuntimeException("Pauta nao encontrada!");
        }
        return pauta
                .stream()
                .map(PautaExibicaoDto::new)
                .toList();
    }

    public List<PautaExibicaoDto> buscarPelaDataEncerramento(LocalDate dataInicial, LocalDate dataFinal) {
        List<PautaModel> pauta = repository.findByDataEncerramentoBetween(dataInicial, dataFinal);

        if (pauta.isEmpty()) {
            throw new RuntimeException("Pauta nao encontrada!");
        }
        return pauta
                .stream()
                .map(PautaExibicaoDto::new)
                .toList();
    }


}
