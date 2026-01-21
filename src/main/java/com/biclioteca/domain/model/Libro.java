package com.biclioteca.domain.model;

import java.util.Objects;

import com.biclioteca.domain.exception.LibroNoDisponibleException;
import com.biclioteca.domain.model.valueobjects.ISBN;

public class Libro {
    private final Long id;
    private final String titulo;
    private final String autor;
    private final ISBN isbn;
    private boolean disponible;

    public Libro(Long id, String titulo, String autor, ISBN isbn, boolean disponible) {
        if (titulo == null || titulo.isBlank()) throw new IllegalArgumentException("Título requerido");
        if (autor == null || autor.isBlank()) throw new IllegalArgumentException("Autor requerido");
        if (isbn == null) throw new IllegalArgumentException("ISBN requerido");

        this.id = id;
        this.titulo = titulo.trim();
        this.autor = autor.trim();
        this.isbn = isbn;
        this.disponible = disponible;
    }

    public void prestar() {
        if (!disponible) {
            throw new LibroNoDisponibleException(id);
        }
        this.disponible = false;
    }

    public void devolver() {
        this.disponible = true;
    }

    public boolean isValid() {
        return titulo != null && !titulo.isBlank()
                && autor != null && !autor.isBlank()
                && isbn != null && ISBN.isValid(isbn.getValor());
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public ISBN getIsbn() { return isbn; }
    public boolean isDisponible() { return disponible; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro libro)) return false;
        return Objects.equals(id, libro.id) && Objects.equals(isbn, libro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn);
    }
}