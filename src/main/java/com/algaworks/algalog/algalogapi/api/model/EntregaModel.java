package com.algaworks.algalog.algalogapi.api.model;

import com.algaworks.algalog.algalogapi.domain.model.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter

public class EntregaModel {
    private Long id;
    private ClienteResumoModel cliente;
//    private String nomeCliente;
    private StatusEntrega status;
    private DestinatarioModel destinatario;
    private BigDecimal taxa;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
}
