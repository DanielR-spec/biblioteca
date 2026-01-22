package com.biblioteca.infrastructure.rest.dto;

import java.time.LocalDate;

public record PrestamoResponse(
        Long prestamoId,
        Long libroId,
        Long usuarioId,
        LocalDate fechaPrestamo,
        LocalDate fechaDevolucion
) { }