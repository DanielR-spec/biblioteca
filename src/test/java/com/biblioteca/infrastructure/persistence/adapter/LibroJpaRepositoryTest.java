package com.biblioteca.infrastructure.persistence.adapter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.biblioteca.domain.model.Libro;
import com.biblioteca.domain.model.valueobjects.ISBN;
import com.biblioteca.infrastructure.persistence.jpa.SpringDataLibroRepository;

@DataJpaTest(properties = {
    "spring.sql.init.mode=never",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
class LibroJpaRepositoryTest {

  @Autowired SpringDataLibroRepository springDataLibroRepository;

  @Test
  void shouldSaveAndFind() {
    springDataLibroRepository.deleteAll();

    LibroJpaRepository repo = new LibroJpaRepository(springDataLibroRepository);
    Libro saved = repo.save(new Libro(null, "X", "Y", new ISBN("9780134494166"), true));

    assertNotNull(saved.getId());
    assertTrue(repo.findById(saved.getId()).isPresent());
  }
}

