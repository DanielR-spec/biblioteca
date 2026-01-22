package com.biblioteca.infrastructure.persistence.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biblioteca.infrastructure.persistence.entity.PrestamoEntity;

public interface SpringDataPrestamoRepository extends JpaRepository<PrestamoEntity, Long> {

  @Query("select p from PrestamoEntity p where p.libroId = :libroId and p.fechaDevolucion is null")
  Optional<PrestamoEntity> findActivoByLibroId(@Param("libroId") Long libroId);
}