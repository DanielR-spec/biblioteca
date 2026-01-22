package com.biblioteca.application.dto;

import java.time.LocalDate;

public record DevolucionResult(Long prestamoId, Long libroId, LocalDate fechaDevolucion) {}

