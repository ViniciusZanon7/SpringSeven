package com.sevenzeal.model;

public enum TipoUsuario {
    CLIENTE,
    ESTETICA,
    ADMIN;

    public static TipoUsuario from(String value) {
        if (value == null) return null;
        try {
            return TipoUsuario.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
