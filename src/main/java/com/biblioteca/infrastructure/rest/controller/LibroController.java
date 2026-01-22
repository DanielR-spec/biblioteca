package com.biblioteca.infrastructure.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.application.dto.BuscarLibrosQuery;
import com.biblioteca.application.usecase.BuscarLibrosUseCaseImpl;
import com.biblioteca.domain.exception.LibroNoEncontradoException;
import com.biblioteca.domain.model.Libro;
import com.biblioteca.domain.model.valueobjects.ISBN;
import com.biblioteca.domain.repository.ILibroRepository;
import com.biblioteca.infrastructure.rest.dto.LibroRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

  private final ILibroRepository libroRepository;

  public LibroController(ILibroRepository libroRepository) {
    this.libroRepository = libroRepository;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Libro create(@RequestBody @Valid LibroRequest request) {
    Libro libro = new Libro(null, request.titulo(), request.autor(), new ISBN(request.isbn()), true);
    return libroRepository.save(libro);
  }

  @GetMapping
  public List<Libro> list() {
    return libroRepository.findAll();
  }

  @GetMapping("/{id}")
  public Libro get(@PathVariable Long id) {
    return libroRepository.findById(id).orElseThrow(() -> new LibroNoEncontradoException(id));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    libroRepository.deleteById(id);
  }
}