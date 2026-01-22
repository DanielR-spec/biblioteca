package com.biblioteca.domain.exception;

public class LibroNoEncontradoException extends RuntimeException {
    public LibroNoEncontradoException(Long id) {
        super("Libro no encontrado con id=" + id);
    }
}