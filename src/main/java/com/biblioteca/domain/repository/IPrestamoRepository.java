package com.biblioteca.domain.repository;

import java.util.List;
import java.util.Optional;

import com.biblioteca.domain.model.Prestamo;

public interface IPrestamoRepository {
    Prestamo save(Prestamo prestamo);
    Optional<Prestamo> findById(Long id);
    Optional<Prestamo> findActivoByLibroId(Long libroId);
    List<Prestamo> findAll();
}