package com.sevenzeal.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "historico_proprietarios")
public class HistoricoProprietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔥 RELAÇÃO COM VEÍCULO
    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    // 🔥 RELAÇÃO COM USUÁRIO
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    public HistoricoProprietario() {}

    public Long getId() { return id; }
    public Veiculo getVeiculo() { return veiculo; }
    public User getUsuario() { return usuario; }
    public LocalDate getDataInicio() { return dataInicio; }
    public LocalDate getDataFim() { return dataFim; }

    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }
    public void setUsuario(User usuario) { this.usuario = usuario; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
}