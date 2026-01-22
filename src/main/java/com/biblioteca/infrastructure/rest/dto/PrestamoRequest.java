package com.biblioteca.infrastructure.rest.dto;

import jakarta.validation.constraints.NotNull;

public record PrestamoRequest(
        @NotNull Long libroId,
        @NotNull Long usuarioId
) { }