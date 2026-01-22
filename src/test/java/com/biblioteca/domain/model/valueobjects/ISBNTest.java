package com.biblioteca.domain.model.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.biblioteca.domain.model.valueobjects.ISBN;

class ISBNTest {

  @Test
  void shouldCreateValidIsbn() {
    ISBN isbn = new ISBN("9780134494166");
    assertTrue(ISBN.isValid("9780134494166"));
    assertEquals("9780134494166", isbn.getValor());
  }

  @Test
  void shouldRejectInvalidIsbn() {
    assertThrows(IllegalArgumentException.class, () -> new ISBN("123"));
    assertThrows(IllegalArgumentException.class, () -> new ISBN(null));
  }
}