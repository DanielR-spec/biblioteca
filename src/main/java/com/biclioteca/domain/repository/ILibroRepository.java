package com.biclioteca.domain.repository;

import java.util.List;
import java.util.Optional;

import com.biclioteca.domain.model.Libro;

public interface ILibroRepository {
    Optional<Libro> findById(Long id);
    Libro save(Libro libro);
    List<Libro> search(String texto); // por titulo/autor/isbn
    void deleteById(Long id);
}