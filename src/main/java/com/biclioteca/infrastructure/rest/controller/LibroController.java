package com.biclioteca.infrastructure.rest.controller;

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

import com.biclioteca.application.dto.BuscarLibrosQuery;
import com.biclioteca.application.service.BuscarLibrosService;
import com.biclioteca.domain.exception.LibroNoEncontradoException;
import com.biclioteca.domain.model.Libro;
import com.biclioteca.domain.model.valueobjects.ISBN;
import com.biclioteca.domain.repository.LibroRepository;
import com.biclioteca.infrastructure.rest.dto.LibroRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroRepository libroRepository;
    private final BuscarLibrosService buscarLibrosUseCase;

    public LibroController(LibroRepository libroRepository, BuscarLibrosService buscarLibrosUseCase) {
        this.libroRepository = libroRepository;
        this.buscarLibrosUseCase = buscarLibrosUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Libro crear(@Valid @RequestBody LibroRequest req) {
        var libro = new Libro(null, req.titulo(), req.autor(), new ISBN(req.isbn()), true);
        return libroRepository.save(libro);
    }

    @GetMapping("/{id}")
    public Libro obtener(@PathVariable Long id) {
        return libroRepository.findById(id).orElseThrow(() -> new LibroNoEncontradoException(id));
    }

    @GetMapping
    public List<Libro> buscar(@RequestParam(required = false) String q) {
        return buscarLibrosUseCase.ejecutar(new BuscarLibrosQuery(q));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        libroRepository.deleteById(id);
    }
}