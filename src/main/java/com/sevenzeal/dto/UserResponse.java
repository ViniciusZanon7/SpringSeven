package com.sevenzeal.dto;

public class UserResponse {
    public Long id;
    public String nome;
    public String email;
    public String tipoUsuario;

    public UserResponse(Long id, String nome, String email, String tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
    }
}