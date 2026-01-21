package com.biclioteca.application.dto;

import java.util.List;

public record ListadoLibrosResult(List<LibroResumen> libros) {

    public record LibroResumen(
            Long id,
            String titulo,
            String autor,
            String isbn,
            boolean disponible
    ) {}
}
