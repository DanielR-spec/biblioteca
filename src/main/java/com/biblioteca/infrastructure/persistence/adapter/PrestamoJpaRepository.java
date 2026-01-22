package com.biblioteca.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.biblioteca.domain.model.Prestamo;
import com.biblioteca.domain.repository.IPrestamoRepository;
import com.biblioteca.infrastructure.persistence.entity.LibroEntity;
import com.biblioteca.infrastructure.persistence.entity.PrestamoEntity;
import com.biblioteca.infrastructure.persistence.entity.UsuarioEntity;
import com.biblioteca.infrastructure.persistence.jpa.SpringDataLibroRepository;
import com.biblioteca.infrastructure.persistence.jpa.SpringDataPrestamoRepository;
import com.biblioteca.infrastructure.persistence.jpa.SpringDataUsuarioRepository;

@Repository
public class PrestamoJpaRepository implements IPrestamoRepository {

  private final SpringDataPrestamoRepository springDataPrestamoRepository;
  private final SpringDataLibroRepository springDataLibroRepository;
  private final SpringDataUsuarioRepository springDataUsuarioRepository;

  public PrestamoJpaRepository(SpringDataPrestamoRepository springDataPrestamoRepository,
                               SpringDataLibroRepository springDataLibroRepository,
                               SpringDataUsuarioRepository springDataUsuarioRepository) {
    this.springDataPrestamoRepository = springDataPrestamoRepository;
    this.springDataLibroRepository = springDataLibroRepository;
    this.springDataUsuarioRepository = springDataUsuarioRepository;
  }

  @Override
  public Prestamo save(Prestamo prestamo) {
    PrestamoEntity entity = toEntity(prestamo);
    PrestamoEntity saved = springDataPrestamoRepository.save(entity);
    return toDomain(saved);
  }

  @Override
  public Optional<Prestamo> findById(Long id) {
    return springDataPrestamoRepository.findById(id).map(this::toDomain);
  }

  @Override
  public Optional<Prestamo> findActivoByLibroId(Long libroId) {
    return springDataPrestamoRepository.findActivoByLibroId(libroId).map(this::toDomain);
  }

  @Override
  public List<Prestamo> findAll() {
    return springDataPrestamoRepository.findAll().stream().map(this::toDomain).toList();
  }

  public Prestamo toDomain(PrestamoEntity entity) {
    return new Prestamo(
        entity.getId(),
        entity.getLibroId(),
        entity.getUsuarioId(),
        entity.getFechaPrestamo(),
        entity.getFechaDevolucion()
    );
  }

  public PrestamoEntity toEntity(Prestamo domain) {
    PrestamoEntity e = new PrestamoEntity();
    e.setId(domain.getId());

    LibroEntity libro = springDataLibroRepository.findById(domain.getLibroId()).orElseThrow(() -> new IllegalArgumentException("Libro no existe"));
    UsuarioEntity usuario = springDataUsuarioRepository.findById(domain.getUsuarioId()).orElseThrow(() -> new IllegalArgumentException("Usuario no existe"));

    e.setLibroId(libro.getId());
    e.setUsuarioId(usuario.getId());
    e.setFechaPrestamo(domain.getFechaPrestamo());
    e.setFechaDevolucion(domain.getFechaDevolucion());
    return e;
  }
}