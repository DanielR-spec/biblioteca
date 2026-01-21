package com.biclioteca.domain.exception;

public class LibroNoDisponibleException extends RuntimeException {
    public LibroNoDisponibleException(Long id) {
        super("Libro no disponible para préstamo. id=" + id);
    }
}