package com.biblioteca.application.dto;

import java.time.LocalDate;

public record PrestamoResult(Long prestamoId, Long libroId, Long usuarioId, LocalDate fechaPrestamo) {}
