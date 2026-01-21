package com.biclioteca.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.biclioteca.domain.model.Libro;
import com.biclioteca.domain.model.valueobjects.ISBN;
import com.biclioteca.domain.repository.ILibroRepository;
import com.biclioteca.infrastructure.persistence.entity.LibroEntity;
import com.biclioteca.infrastructure.persistence.jpa.SpringDataLibroRepository;

@Repository
public class LibroJpaRepository implements ILibroRepository {

    private final SpringDataLibroRepository jpa;

    public LibroJpaRepository(SpringDataLibroRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<Libro> findById(Long id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public Libro save(Libro libro) {
        LibroEntity saved = jpa.save(toEntity(libro));
        return toDomain(saved);
    }

    @Override
    public List<Libro> search(String texto) {
        if (texto == null) texto = "";
        var list = jpa.findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCaseOrIsbnContainingIgnoreCase(
                texto, texto, texto
        );
        return list.stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }

    private Libro toDomain(LibroEntity e) {
        return new Libro(
                e.getId(),
                e.getTitulo(),
                e.getAutor(),
                new ISBN(e.getIsbn()),
                e.isDisponible()
        );
    }

    private LibroEntity toEntity(Libro d) {
        var e = new LibroEntity();
        e.setId(d.getId());
        e.setTitulo(d.getTitulo());
        e.setAutor(d.getAutor());
        e.setIsbn(d.getIsbn().getValor());
        e.setDisponible(d.isDisponible());
        return e;
    }
}