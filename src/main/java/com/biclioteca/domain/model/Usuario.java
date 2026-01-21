package com.biclioteca.domain.model;

public class Usuario {
    private final Long id;
    private final String nombre;
    private final String email;
    private final String rol;

    public Usuario(Long id, String nombre, String email, String rol) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre requerido");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email requerido");
        if (rol == null || rol.isBlank()) throw new IllegalArgumentException("Rol requerido");

        this.id = id;
        this.nombre = nombre.trim();
        this.email = email.trim().toLowerCase();
        this.rol = rol.trim().toUpperCase();
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getRol() { return rol; }
}