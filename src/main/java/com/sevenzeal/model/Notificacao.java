package com.sevenzeal.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "notificacoes")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    private String mensagem;
    private String status;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    public Notificacao() {}

    public Long getId() { return id; }
    public Long getUsuarioId() { return usuarioId; }
    public String getMensagem() { return mensagem; }
    public String getStatus() { return status; }
    public LocalDateTime getCriadoEm() { return criadoEm; }

    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public void setStatus(String status) { this.status = status; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}
