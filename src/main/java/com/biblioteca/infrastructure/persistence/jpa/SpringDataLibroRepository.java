package com.biblioteca.infrastructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biblioteca.infrastructure.persistence.entity.LibroEntity;


public interface SpringDataLibroRepository extends JpaRepository<LibroEntity, Long> {
  @Query("""
      select l from LibroEntity l
      where (:titulo is null or lower(l.titulo) like lower(concat('%', :titulo, '%')))
        and (:autor is null or lower(l.autor) like lower(concat('%', :autor, '%')))
        and (:isbn is null or l.isbn = :isbn)
      """)
  List<LibroEntity> search(String titulo, String autor, String isbn);
}
