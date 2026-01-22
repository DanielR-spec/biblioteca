package com.biblioteca.application.dto;

import java.util.List;

public record ListadoLibrosResult(List<LibroItem> libros) {
  public record LibroItem(Long id, String titulo, String autor, String isbn, boolean disponible) {}
}
