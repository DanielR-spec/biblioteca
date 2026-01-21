package com.biclioteca.domain.repository;

import java.util.List;
import java.util.Optional;

import com.biclioteca.domain.model.Prestamo;

public interface IPrestamoRepository {
    Prestamo save(Prestamo prestamo);
    Optional<Prestamo> findById(Long id);
    Optional<Prestamo> findActivoByLibroId(Long libroId);
    List<Prestamo> findByUsuarioId(Long usuarioId);
}