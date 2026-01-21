package com.biclioteca.infrastructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biclioteca.infrastructure.persistence.entity.LibroEntity;

public interface SpringDataLibroRepository extends JpaRepository<LibroEntity, Long> {
    List<LibroEntity> findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCaseOrIsbnContainingIgnoreCase(
            String titulo, String autor, String isbn
    );
}
