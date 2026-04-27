package com.sevenzeal.dto;

import java.time.LocalDateTime;

public class EsteticaResponse {

    public Long id;
    public Long usuarioId;
    public String nome;
    public String endereco;
    public String cidade;
    public String telefone;

    public String logoUrl;
    public String corPrimaria;
    public String corSecundaria;
    public String corFundo;

    public String whatsappUrl;
    public String mapsUrl;
    public String instagramUrl;

    public LocalDateTime criadoEm;

    public EsteticaResponse(
        Long id,
        Long usuarioId,
        String nome,
        String endereco,
        String cidade,
        String telefone,
        String logoUrl,
        String corPrimaria,
        String corSecundaria,
        String corFundo,
        String whatsappUrl,
        String mapsUrl,
        String instagramUrl,
        LocalDateTime criadoEm
    ) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.telefone = telefone;

        this.logoUrl = logoUrl;
        this.corPrimaria = corPrimaria;
        this.corSecundaria = corSecundaria;
        this.corFundo = corFundo;

        this.whatsappUrl = whatsappUrl;
        this.mapsUrl = mapsUrl;
        this.instagramUrl = instagramUrl;

        this.criadoEm = criadoEm;
    }
}