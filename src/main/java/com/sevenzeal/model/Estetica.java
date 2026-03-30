package com.sevenzeal.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "esteticas")
public class Estetica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    private String nome;
    private String endereco;
    private String cidade;
    private String telefone;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    public Estetica() {}

    public Long getId() { return id; }
    public Long getUsuarioId() { return usuarioId; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getCidade() { return cidade; }
    public String getTelefone() { return telefone; }
    public LocalDateTime getCriadoEm() { return criadoEm; }

    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}
