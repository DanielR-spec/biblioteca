package com.biclioteca.infrastructure.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.biclioteca.application.dto.DevolverLibroCommand;
import com.biclioteca.application.dto.PrestarLibroCommand;
import com.biclioteca.application.usecase.DevolverLibroUseCaseImpl;
import com.biclioteca.application.usecase.PrestarLibroUseCaseImpl;
import com.biclioteca.infrastructure.rest.dto.PrestamoRequest;
import com.biclioteca.infrastructure.rest.dto.PrestamoResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestarLibroUseCaseImpl prestarLibroUseCase;
    private final DevolverLibroUseCaseImpl devolverLibroUseCase;

    public PrestamoController(PrestarLibroUseCaseImpl prestarLibroUseCase,
                              DevolverLibroUseCaseImpl devolverLibroUseCase) {
        this.prestarLibroUseCase = prestarLibroUseCase;
        this.devolverLibroUseCase = devolverLibroUseCase;
    }

    @PostMapping("/prestar")
    @ResponseStatus(HttpStatus.CREATED)
    public PrestamoResponse prestar(@Valid @RequestBody PrestamoRequest req) {
        var result = prestarLibroUseCase.ejecutar(new PrestarLibroCommand(req.libroId(), req.usuarioId(), null));
        return new PrestamoResponse(result.prestamoId(), result.libroId(), result.usuarioId(),
                result.fechaPrestamo(), result.fechaDevolucion());
    }

    @PostMapping("/{prestamoId}/devolver")
    public PrestamoResponse devolver(@PathVariable Long prestamoId) {
        var result = devolverLibroUseCase.ejecutar(new DevolverLibroCommand(prestamoId, null));
        return new PrestamoResponse(result.prestamoId(), result.libroId(), result.usuarioId(),
                result.fechaPrestamo(), result.fechaDevolucion());
    }
}
