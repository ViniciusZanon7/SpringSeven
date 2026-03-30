package com.sevenzeal.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoResponse {

    public Long id;
    public Long usuarioId;
    public Long veiculoId;
    public LocalDate data;
    public LocalTime hora;
    public String status;

    public AgendamentoResponse(Long id, Long usuarioId, Long veiculoId, LocalDate data, LocalTime hora, String status) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.veiculoId = veiculoId;
        this.data = data;
        this.hora = hora;
        this.status = status;
    }
}