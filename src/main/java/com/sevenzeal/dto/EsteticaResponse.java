package com.sevenzeal.dto;

import java.time.LocalDateTime;

public class EsteticaResponse {
    public Long id;
    public Long usuarioId;
    public String nome;
    public String endereco;
    public String cidade;
    public String telefone;
    public LocalDateTime criadoEm;

    public EsteticaResponse(Long id, Long usuarioId, String nome, String endereco, String cidade, String telefone, LocalDateTime criadoEm) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.telefone = telefone;
        this.criadoEm = criadoEm;
    }
}
