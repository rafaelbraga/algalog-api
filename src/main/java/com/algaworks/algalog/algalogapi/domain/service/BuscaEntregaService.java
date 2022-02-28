package com.algaworks.algalog.algalogapi.domain.service;

import com.algaworks.algalog.algalogapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalog.algalogapi.domain.exception.NegocioException;
import com.algaworks.algalog.algalogapi.domain.model.Entrega;
import com.algaworks.algalog.algalogapi.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
    private EntregaRepository entregaRepository;

    public Entrega buscar (Long entregaId){
        return  entregaRepository.findById(entregaId)
                .orElseThrow(()-> new EntidadeNaoEncontradaException("Entrega não Encontrada!"));
    }
}
