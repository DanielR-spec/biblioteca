package com.biclioteca.application.dto;

import java.time.LocalDate;

public record DevolucionResult(Long libroId, boolean devuelto) {

    public Long prestamoId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'prestamoId'");
    }

    public Long usuarioId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'usuarioId'");
    }

    public LocalDate fechaPrestamo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fechaPrestamo'");
    }

    public LocalDate fechaDevolucion() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fechaDevolucion'");
    }}
