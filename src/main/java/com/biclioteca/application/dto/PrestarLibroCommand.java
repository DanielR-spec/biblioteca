package com.biclioteca.application.dto;

import java.time.LocalDate;

public record PrestarLibroCommand(Long libroId, Long usuarioId, LocalDate fechaPrestamo) { }
