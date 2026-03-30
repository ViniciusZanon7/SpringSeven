package com.sevenzeal.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoRequest {
    public Long usuarioId;
    public Long veiculoId;
    public Long esteticaId;
    public Long servicoId;
    public LocalDate dataAgendamento;
    public LocalTime horaAgendamento;
}