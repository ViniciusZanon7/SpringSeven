package com.sevenzeal.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "servicos")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estetica_id")
    private Long esteticaId;

    private String nome;
    private String descricao;
    private BigDecimal preco;

    @Column(name = "duracao_minutos")
    private Integer duracaoMinutos;

    @Column(name = "duracao_dias")
    private Integer duracaoDias;

    @Column(name = "intervalo_manutencao_dias")
    private Integer intervaloManutencaoDias;

    public Servico() {}

    public Long getId() { return id; }
    public Long getEsteticaId() { return esteticaId; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public BigDecimal getPreco() { return preco; }
    public Integer getDuracaoMinutos() { return duracaoMinutos; }
    public Integer getDuracaoDias() { return duracaoDias; }
    public Integer getIntervaloManutencaoDias() { return intervaloManutencaoDias; }

    public void setEsteticaId(Long esteticaId) { this.esteticaId = esteticaId; }
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public void setDuracaoMinutos(Integer duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }
    public void setDuracaoDias(Integer duracaoDias) { this.duracaoDias = duracaoDias; }
    public void setIntervaloManutencaoDias(Integer intervaloManutencaoDias) { this.intervaloManutencaoDias = intervaloManutencaoDias; }
}
