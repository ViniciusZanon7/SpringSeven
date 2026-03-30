package com.sevenzeal.dto;

import java.time.LocalDate;

public class EstatisticaVeiculoResponse {
    public Long veiculoId;
    public Long totalServicos;
    public LocalDate ultimaDataServico;
    public LocalDate proximaManutencao;

    public EstatisticaVeiculoResponse(Long veiculoId, Long totalServicos, LocalDate ultimaDataServico, LocalDate proximaManutencao) {
        this.veiculoId = veiculoId;
        this.totalServicos = totalServicos;
        this.ultimaDataServico = ultimaDataServico;
        this.proximaManutencao = proximaManutencao;
    }
}
