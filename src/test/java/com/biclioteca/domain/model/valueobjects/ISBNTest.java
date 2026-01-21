package com.biclioteca.domain.model.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ISBNTest {

    @Test
    void shouldCreateValidIsbn() {
        var isbn = new ISBN("9781234567890");
        assertEquals("9781234567890", isbn.getValor());
    }

    @Test
    void shouldNormalizeHyphens() {
        var isbn = new ISBN("978-1234567890");
        assertEquals("9781234567890", isbn.getValor());
    }

    @Test
    void shouldRejectInvalidIsbn() {
        assertThrows(IllegalArgumentException.class, () -> new ISBN("123"));
        assertThrows(IllegalArgumentException.class, () -> new ISBN("ABCDEFGHIJKLM"));
    }
}