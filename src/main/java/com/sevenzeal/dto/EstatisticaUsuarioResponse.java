package com.sevenzeal.dto;

public class EstatisticaUsuarioResponse {
    public Long usuarioId;
    public Long totalVeiculos;
    public Long totalServicos;

    public EstatisticaUsuarioResponse(Long usuarioId, Long totalVeiculos, Long totalServicos) {
        this.usuarioId = usuarioId;
        this.totalVeiculos = totalVeiculos;
        this.totalServicos = totalServicos;
    }
}
