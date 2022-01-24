package com.algaworks.algalog.algalogapi.domain.service;

import com.algaworks.algalog.algalogapi.domain.model.Cliente;
import com.algaworks.algalog.algalogapi.domain.model.Entrega;
import com.algaworks.algalog.algalogapi.domain.model.StatusEntrega;
import com.algaworks.algalog.algalogapi.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {
    private EntregaRepository entregaRepository;
//    private ClienteRepository clienteRepository;
    private  CatalogoClienteService catalogoClienteService;

    @Transactional
    public Entrega solicitar(Entrega entrega){
        Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());
        return entregaRepository.save(entrega);
    }
}
