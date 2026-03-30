package com.sevenzeal.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "historico_servicos")
public class HistoricoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "veiculo_id")
    private Long veiculoId;

    @Column(name = "servico_id")
    private Long servicoId;

    @Column(name = "estetica_id")
    private Long esteticaId;

    @Column(name = "data_realizacao")
    private LocalDate dataRealizacao;

    @Column(name = "proxima_manutencao")
    private LocalDate proximaManutencao;

    public HistoricoServico() {}

    public Long getId() { return id; }
    public Long getVeiculoId() { return veiculoId; }
    public Long getServicoId() { return servicoId; }
    public Long getEsteticaId() { return esteticaId; }
    public LocalDate getDataRealizacao() { return dataRealizacao; }
    public LocalDate getProximaManutencao() { return proximaManutencao; }

    public void setVeiculoId(Long veiculoId) { this.veiculoId = veiculoId; }
    public void setServicoId(Long servicoId) { this.servicoId = servicoId; }
    public void setEsteticaId(Long esteticaId) { this.esteticaId = esteticaId; }
    public void setDataRealizacao(LocalDate dataRealizacao) { this.dataRealizacao = dataRealizacao; }
    public void setProximaManutencao(LocalDate proximaManutencao) { this.proximaManutencao = proximaManutencao; }
}
