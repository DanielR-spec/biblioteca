package com.biclioteca.application.dto;

import java.time.LocalDate;
import java.util.function.BooleanSupplier;

public record PrestamoResult(Long prestamoId, Long libroId, Long usuarioId,
                             LocalDate fechaPrestamo, LocalDate fechaDevolucion) {

    public BooleanSupplier prestado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'prestado'");
    } }