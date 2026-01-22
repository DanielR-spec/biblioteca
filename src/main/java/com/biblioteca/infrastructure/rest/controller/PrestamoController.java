package com.biblioteca.infrastructure.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.application.dto.DevolucionResult;
import com.biblioteca.application.dto.DevolverLibroCommand;
import com.biblioteca.application.dto.PrestamoResult;
import com.biblioteca.application.dto.PrestarLibroCommand;
import com.biblioteca.application.usecase.Interfaces.IDevolverLibroUseCase;
import com.biblioteca.application.usecase.Interfaces.IPrestarLibroUseCase;
import com.biblioteca.domain.model.Prestamo;
import com.biblioteca.domain.repository.IPrestamoRepository;
import com.biblioteca.infrastructure.rest.dto.PrestamoRequest;
import com.biblioteca.infrastructure.rest.dto.PrestamoResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

  private final IPrestarLibroUseCase prestarLibroUseCase;
  private final IDevolverLibroUseCase devolverLibroUseCase;
  private final IPrestamoRepository prestamoRepository;

  public PrestamoController(IPrestarLibroUseCase prestarLibroUseCase, IDevolverLibroUseCase devolverLibroUseCase, IPrestamoRepository prestamoRepository) {
    this.prestarLibroUseCase = prestarLibroUseCase;
    this.devolverLibroUseCase = devolverLibroUseCase;
    this.prestamoRepository = prestamoRepository;
  }

  @PostMapping("/prestar")
  @ResponseStatus(HttpStatus.CREATED)
  public PrestamoResponse prestar(@RequestBody @Valid PrestamoRequest request) {
    PrestamoResult r = prestarLibroUseCase.ejecutar(new PrestarLibroCommand(request.libroId(), request.usuarioId()));
    return new PrestamoResponse(r.prestamoId(), r.libroId(), r.usuarioId(), r.fechaPrestamo(), null);
  }

  @PostMapping("/devolver/{libroId}")
  public PrestamoResponse devolver(@PathVariable Long libroId) {
    DevolucionResult r = devolverLibroUseCase.ejecutar(new DevolverLibroCommand(libroId));
    return new PrestamoResponse(r.prestamoId(), r.libroId(), null, null, r.fechaDevolucion());
  }

  @GetMapping
  public List<Prestamo> list() {
    return prestamoRepository.findAll();
  }
}
