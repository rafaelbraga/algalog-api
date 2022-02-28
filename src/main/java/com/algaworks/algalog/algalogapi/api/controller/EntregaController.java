package com.algaworks.algalog.algalogapi.api.controller;

import com.algaworks.algalog.algalogapi.api.model.DestinatarioModel;
import com.algaworks.algalog.algalogapi.api.model.EntregaModel;
import com.algaworks.algalog.algalogapi.api.model.input.EntregaInput;
import com.algaworks.algalog.algalogapi.assembler.EntregaAssembler;
import com.algaworks.algalog.algalogapi.domain.model.Entrega;
import com.algaworks.algalog.algalogapi.domain.repository.EntregaRepository;
import com.algaworks.algalog.algalogapi.domain.service.FinalizacaoEntregaService;
import com.algaworks.algalog.algalogapi.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private SolicitacaoEntregaService solicitacaoEntregaService;
    private FinalizacaoEntregaService finalizacaoEntregaService;
    private EntregaRepository entregaRepository;
    private EntregaAssembler entregaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitar( @Valid @RequestBody EntregaInput entregaInput){
        return entregaAssembler.toModel(solicitacaoEntregaService.solicitar(entregaAssembler.toEntity(entregaInput)));
    }

    @GetMapping
    public List<EntregaModel> listar(){
        return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId){
        return entregaRepository.findById(entregaId).map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega))).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId){
        finalizacaoEntregaService.finalizar(entregaId);

    }
}
