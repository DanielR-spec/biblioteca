package com.biclioteca.infrastructure.persistence.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biclioteca.infrastructure.persistence.entity.PrestamoEntity;

public interface SpringDataPrestamoRepository extends JpaRepository<PrestamoEntity, Long> {
    Optional<PrestamoEntity> findFirstByLibroIdAndFechaDevolucionIsNull(Long libroId);
    List<PrestamoEntity> findByUsuarioId(Long usuarioId);
}