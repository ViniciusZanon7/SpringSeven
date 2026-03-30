package com.sevenzeal.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "historico_proprietarios")
public class HistoricoProprietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "veiculo_id")
    private Long veiculoId;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    public HistoricoProprietario() {}

    public Long getId() { return id; }
    public Long getVeiculoId() { return veiculoId; }
    public Long getUsuarioId() { return usuarioId; }
    public LocalDate getDataInicio() { return dataInicio; }
    public LocalDate getDataFim() { return dataFim; }

    public void setVeiculoId(Long veiculoId) { this.veiculoId = veiculoId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
}