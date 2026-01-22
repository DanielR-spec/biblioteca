package com.biblioteca.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biblioteca.application.dto.PrestarLibroCommand;
import com.biblioteca.application.usecase.Interfaces.IPrestarLibroUseCase;
import com.biblioteca.domain.repository.ILibroRepository;
import com.biblioteca.domain.repository.IUsuarioRepository;

@SpringBootTest
class PrestamoFlowIntegrationTest {

  @Autowired IPrestarLibroUseCase prestarLibroUseCase;
  @Autowired ILibroRepository libroRepository;
  @Autowired IUsuarioRepository usuarioRepository;

  @Test
  void shouldCreateLoanAndMarkBookNotAvailable() {
    Long libroId = 1L;
    Long usuarioId = 1L;

    assertTrue(libroRepository.findById(libroId).orElseThrow().isDisponible());
    usuarioRepository.findById(usuarioId).orElseThrow();

    var res = prestarLibroUseCase.ejecutar(new PrestarLibroCommand(libroId, usuarioId));

    assertNotNull(res.prestamoId());
    assertFalse(libroRepository.findById(libroId).orElseThrow().isDisponible());
  }
}