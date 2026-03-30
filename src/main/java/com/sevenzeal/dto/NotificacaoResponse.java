package com.sevenzeal.dto;

import java.time.LocalDateTime;

public class NotificacaoResponse {
    public Long id;
    public Long usuarioId;
    public String mensagem;
    public String status;
    public LocalDateTime criadoEm;

    public NotificacaoResponse(Long id, Long usuarioId, String mensagem, String status, LocalDateTime criadoEm) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.mensagem = mensagem;
        this.status = status;
        this.criadoEm = criadoEm;
    }
}
