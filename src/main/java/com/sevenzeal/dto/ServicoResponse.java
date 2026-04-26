package com.sevenzeal.dto;

import java.math.BigDecimal;

public class ServicoResponse {
    public Long id;
    public Long esteticaId;
    public String nome;
    public String descricao;
    public BigDecimal preco;
    public Integer duracaoMinutos;
    public Integer duracaoDias;
    public Integer intervaloManutencaoDias;

    public ServicoResponse(Long id, Long esteticaId, String nome, String descricao, BigDecimal preco, Integer duracaoMinutos, Integer duracaoDias, Integer intervaloManutencaoDias) {
        this.id = id;
        this.esteticaId = esteticaId;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.duracaoMinutos = duracaoMinutos;
        this.duracaoDias = duracaoDias;
        this.intervaloManutencaoDias = intervaloManutencaoDias;
    }
}
