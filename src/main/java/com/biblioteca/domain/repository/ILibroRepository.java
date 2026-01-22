package com.biblioteca.domain.repository;

import java.util.List;
import java.util.Optional;

import com.biblioteca.domain.model.Libro;

public interface ILibroRepository {
    Libro save(Libro libro);
    Optional<Libro> findById(Long id);
    List<Libro> findAll();
    List<Libro> search(String titulo, String autor, String isbn);
    void deleteById(Long id);
}