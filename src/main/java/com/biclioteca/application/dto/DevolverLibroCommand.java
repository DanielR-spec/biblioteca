package com.biclioteca.application.dto;

import java.time.LocalDate;

public record DevolverLibroCommand(Long prestamoId, LocalDate fechaDevolucion) { }
