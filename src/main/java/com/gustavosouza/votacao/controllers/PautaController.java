package com.gustavosouza.votacao.controllers;

import com.gustavosouza.votacao.dto.PautaCadastroDto;
import com.gustavosouza.votacao.dto.PautaExibicaoDto;
import com.gustavosouza.votacao.model.PautaModel;
import com.gustavosouza.votacao.services.PautaService;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pauta")
@RequiredArgsConstructor
public class PautaController {

    private final PautaService pautaService;


    @PostMapping("/cadastrar")
    public ResponseEntity<PautaExibicaoDto> cadastrarPauta(@RequestBody @Valid PautaCadastroDto pautaDto){
        PautaExibicaoDto pauta = pautaService.criarPauta(pautaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pauta);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PautaExibicaoDto> atualizarPauta(@PathVariable Long id, @RequestBody @Valid PautaModel pautaModel){
        pautaService.atualizarPauta(id, pautaModel);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/deletar/id/{id}")
    public ResponseEntity<Void> deletarPautaPorId(@PathVariable Long id){
        pautaService.excluirPauta(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/deletar/assunto/{assunto}")
    public ResponseEntity<Void> deletarPautaPorAssunto(@PathVariable String assunto){
        pautaService.excluirPautaPeloAssunto(assunto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping ("/buscar")
    public ResponseEntity<List<PautaExibicaoDto>> buscarTodasPautas(){
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.buscarTodasPautas());
    }

    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<PautaModel> buscarPautaPeloId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.buscarPautaPeloId(id));
    }

    @GetMapping("/buscar/assunto/{assunto}")
    public ResponseEntity<PautaExibicaoDto> buscarPautaPeloAssunto(@PathVariable String assunto){
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.buscarPautaPeloAssunto(assunto));
    }


    @GetMapping("/buscar/votos/{quantidadeDeVotosNecessarios}")
    public ResponseEntity<List<PautaExibicaoDto>> buscarPautaPeloNumeroDeVotos(@RequestParam Integer quantidadeDeVotosNecessarios){
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.buscarPautaPeloNumeroDeVotos(quantidadeDeVotosNecessarios));
    }

    @GetMapping("/buscar/dataInicio/{primeiraData}/{segundaData}")
    public ResponseEntity<List<PautaExibicaoDto>> buscarPelaDataInicio(@PathVariable LocalDate primeiraData, @PathVariable LocalDate segundaData){
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.buscarPelaDataInicio(primeiraData, segundaData));
    }

    @GetMapping("buscar/dataEncerramento/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<PautaExibicaoDto>> buscarPelaDataEncerramento(@PathVariable LocalDate dataInicial, @PathVariable LocalDate dataFinal){
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.buscarPelaDataEncerramento(dataInicial,dataFinal));
    }


}
