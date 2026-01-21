package com.biclioteca.domain.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.biclioteca.domain.exception.LibroNoDisponibleException;
import com.biclioteca.domain.model.valueobjects.ISBN;

class LibroTest {

    @Test
    void prestarShouldMarkNotAvailable() {
        var libro = new Libro(1L, "Clean Code", "Robert C. Martin", new ISBN("9781234567890"), true);
        libro.prestar();
        assertFalse(libro.isDisponible());
    }

    @Test
    void prestarNotAvailableShouldThrow() {
        var libro = new Libro(1L, "DDD", "Evans", new ISBN("9781234567890"), false);
        assertThrows(LibroNoDisponibleException.class, libro::prestar);
    }

    @Test
    void devolverShouldMarkAvailable() {
        var libro = new Libro(1L, "Refactoring", "Fowler", new ISBN("9781234567890"), false);
        libro.devolver();
        assertTrue(libro.isDisponible());
    }
}
