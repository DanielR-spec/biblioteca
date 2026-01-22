package com.biblioteca.domain.model;

import java.time.LocalDate;
import java.util.Objects;

public class Prestamo {
    private final Long id;
    private final Long libroId;
    private final Long usuarioId;
    private final LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamo(Long id, Long libroId, Long usuarioId, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        if (libroId == null) throw new IllegalArgumentException("libroId requerido");
        if (usuarioId == null) throw new IllegalArgumentException("usuarioId requerido");
        if (fechaPrestamo == null) throw new IllegalArgumentException("fechaPrestamo requerida");

        this.id = id;
        this.libroId = libroId;
        this.usuarioId = usuarioId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public void devolver(LocalDate fecha) {
        if (fecha == null) throw new IllegalArgumentException("fechaDevolucion requerida");
        if (this.fechaDevolucion != null) return; // idempotente
        this.fechaDevolucion = fecha;
    }

    public boolean estaActivo() {
        return fechaDevolucion == null;
    }

    public Long getId() { return id; }
    public Long getLibroId() { return libroId; }
    public Long getUsuarioId() { return usuarioId; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public LocalDate getFechaDevolucion() { return fechaDevolucion; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prestamo prestamo)) return false;
        return Objects.equals(id, prestamo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}