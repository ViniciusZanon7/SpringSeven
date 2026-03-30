package com.sevenzeal.dto;

public class VeiculoResponse {
    public Long id;
    public String tipoVeiculo;
    public String marca;
    public String modelo;
    public String placa;
    public String codigoPublico;

    public VeiculoResponse(Long id, String tipoVeiculo, String marca, String modelo, String placa, String codigoPublico) {
        this.id = id;
        this.tipoVeiculo = tipoVeiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.codigoPublico = codigoPublico;
    }
}