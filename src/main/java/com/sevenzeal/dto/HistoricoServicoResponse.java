package com.sevenzeal.dto;

import java.time.LocalDate;

public class HistoricoServicoResponse {
    public Long id;
    public Long veiculoId;
    public Long servicoId;
    public Long esteticaId;
    public LocalDate dataRealizacao;
    public LocalDate proximaManutencao;

    public HistoricoServicoResponse(Long id, Long veiculoId, Long servicoId, Long esteticaId, LocalDate dataRealizacao, LocalDate proximaManutencao) {
        this.id = id;
        this.veiculoId = veiculoId;
        this.servicoId = servicoId;
        this.esteticaId = esteticaId;
        this.dataRealizacao = dataRealizacao;
        this.proximaManutencao = proximaManutencao;
    }
}
