package com.biblioteca.infrastructure.rest.controller;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.biblioteca.application.service.BuscarLibrosService;
import com.biblioteca.application.usecase.Interfaces.IBuscarLibrosUseCase;
import com.biblioteca.domain.model.Libro;
import com.biblioteca.domain.model.valueobjects.ISBN;
import com.biblioteca.domain.repository.ILibroRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = LibroController.class)
class LibroControllerTest {

  @Autowired MockMvc mvc;

  @MockBean ILibroRepository libroRepository;
  @MockBean IBuscarLibrosUseCase buscarLibrosUseCase;

  @Test
  void shouldReturnBook() throws Exception {
    when(libroRepository.findById(1L))
        .thenReturn(Optional.of(new Libro(1L, "T", "A", new ISBN("9780134494166"), true)));

    mvc.perform(get("/api/libros/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.isbn.valor").value("9780134494166"));
  }
}
