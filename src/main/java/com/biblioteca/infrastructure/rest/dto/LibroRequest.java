package com.biblioteca.infrastructure.rest.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LibroRequest(
        @NotBlank String titulo,
        @NotBlank String autor,
        @NotBlank @Pattern(regexp = "^\\d{13}$") String isbn
) { }