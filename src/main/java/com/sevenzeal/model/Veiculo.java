package com.sevenzeal.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_veiculo", nullable = false)
    private String tipoVeiculo;

    private String marca;
    private String modelo;
    private Integer ano;

    @Column(unique = true)
    private String placa;

    @Column(name = "codigo_publico", unique = true)
    private String codigoPublico;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    public Veiculo() {}

    public Long getId() { return id; }
    public String getTipoVeiculo() { return tipoVeiculo; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public Integer getAno() { return ano; }
    public String getPlaca() { return placa; }
    public String getCodigoPublico() { return codigoPublico; }

    public void setTipoVeiculo(String tipoVeiculo) { this.tipoVeiculo = tipoVeiculo; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setAno(Integer ano) { this.ano = ano; }
    public void setPlaca(String placa) { this.placa = placa; }
    public void setCodigoPublico(String codigoPublico) { this.codigoPublico = codigoPublico; }
}