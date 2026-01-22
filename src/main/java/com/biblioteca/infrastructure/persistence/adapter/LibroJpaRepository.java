package com.biblioteca.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.biblioteca.domain.model.Libro;
import com.biblioteca.domain.model.valueobjects.ISBN;
import com.biblioteca.domain.repository.ILibroRepository;
import com.biblioteca.infrastructure.persistence.entity.LibroEntity;
import com.biblioteca.infrastructure.persistence.jpa.SpringDataLibroRepository;

@Repository
public class LibroJpaRepository implements ILibroRepository {

  private final SpringDataLibroRepository springDataLibroRepository;

  public LibroJpaRepository(SpringDataLibroRepository springDataLibroRepository) {
    this.springDataLibroRepository = springDataLibroRepository;
  }

  @Override
  public Libro save(Libro libro) {
    LibroEntity entity = toEntity(libro);
    LibroEntity saved = springDataLibroRepository.save(entity);
    return toDomain(saved);
  }

  @Override
  public Optional<Libro> findById(Long id) {
    return springDataLibroRepository.findById(id).map(this::toDomain);
  }

  @Override
  public List<Libro> findAll() {
    return springDataLibroRepository.findAll().stream().map(this::toDomain).toList();
  }

  @Override
  public List<Libro> search(String titulo, String autor, String isbn) {
    String t = titulo != null && !titulo.isBlank() ? titulo.trim() : null;
    String a = autor != null && !autor.isBlank() ? autor.trim() : null;
    String i = isbn != null && !isbn.isBlank() ? isbn.trim() : null;
    return springDataLibroRepository.search(t, a, i).stream().map(this::toDomain).toList();
  }

  @Override
  public void deleteById(Long id) {
    springDataLibroRepository.deleteById(id);
  }

  public Libro toDomain(LibroEntity entity) {
    return new Libro(entity.getId(), entity.getTitulo(), entity.getAutor(), new ISBN(entity.getIsbn()), entity.isDisponible());
  }

  public LibroEntity toEntity(Libro domain) {
    LibroEntity e = new LibroEntity();
    e.setId(domain.getId());
    e.setTitulo(domain.getTitulo());
    e.setAutor(domain.getAutor());
    e.setIsbn(domain.getIsbn().getValor());
    e.setDisponible(domain.isDisponible());
    return e;
  }
}