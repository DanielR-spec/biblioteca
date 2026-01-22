package com.biblioteca.domain.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.biblioteca.domain.exception.LibroNoDisponibleException;
import com.biblioteca.domain.model.Libro;
import com.biblioteca.domain.model.valueobjects.ISBN;

class LibroTest {

  @Test
  void shouldPrestamoAndDevolucionChangeAvailability() {
    Libro libro = new Libro(1L, "T", "A", new ISBN("9780134494166"), true);
    assertTrue(libro.isDisponible());

    libro.prestar();
    assertFalse(libro.isDisponible());

    libro.devolver();
    assertTrue(libro.isDisponible());
  }

  @Test
  void shouldFailIfNotAvailable() {
    Libro libro = new Libro(1L, "T", "A", new ISBN("9780134494166"), false);
    assertThrows(LibroNoDisponibleException.class, libro::prestar);
  }

  @Test
  void shouldValidateConstructor() {
    assertThrows(IllegalArgumentException.class, () -> new Libro(1L, "", "A", new ISBN("9780134494166"), true));
    assertThrows(IllegalArgumentException.class, () -> new Libro(1L, "T", "", new ISBN("9780134494166"), true));
  }
}
