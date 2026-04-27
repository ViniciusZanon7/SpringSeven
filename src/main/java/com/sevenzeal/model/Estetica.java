package com.sevenzeal.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "esteticas")
public class Estetica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    private String nome;
    private String endereco;
    private String cidade;
    private String telefone;

    // 🎨 PERSONALIZAÇÃO
    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "cor_primaria")
    private String corPrimaria;

    @Column(name = "cor_secundaria")
    private String corSecundaria;

    @Column(name = "cor_fundo")
    private String corFundo;

    // 🔗 LINKS
    @Column(name = "whatsapp_url")
    private String whatsappUrl;

    @Column(name = "maps_url")
    private String mapsUrl;

    @Column(name = "instagram_url")
    private String instagramUrl;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    public Estetica() {}

    public Long getId() { return id; }

    public User getUsuario() { return usuario; }

    // 🔥 ESSA LINHA RESOLVE SEU ERRO
    public Long getUsuarioId() {
        return usuario != null ? usuario.getId() : null;
    }

    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getCidade() { return cidade; }
    public String getTelefone() { return telefone; }

    public String getLogoUrl() { return logoUrl; }
    public String getCorPrimaria() { return corPrimaria; }
    public String getCorSecundaria() { return corSecundaria; }
    public String getCorFundo() { return corFundo; }

    public String getWhatsappUrl() { return whatsappUrl; }
    public String getMapsUrl() { return mapsUrl; }
    public String getInstagramUrl() { return instagramUrl; }

    public LocalDateTime getCriadoEm() { return criadoEm; }

    public void setUsuario(User usuario) { this.usuario = usuario; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
    public void setCorPrimaria(String corPrimaria) { this.corPrimaria = corPrimaria; }
    public void setCorSecundaria(String corSecundaria) { this.corSecundaria = corSecundaria; }
    public void setCorFundo(String corFundo) { this.corFundo = corFundo; }

    public void setWhatsappUrl(String whatsappUrl) { this.whatsappUrl = whatsappUrl; }
    public void setMapsUrl(String mapsUrl) { this.mapsUrl = mapsUrl; }
    public void setInstagramUrl(String instagramUrl) { this.instagramUrl = instagramUrl; }

    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}