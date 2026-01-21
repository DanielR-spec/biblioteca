package com.biclioteca.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biclioteca.application.dto.BuscarLibrosQuery;
import com.biclioteca.application.usecase.IBuscarLibrosUseCase;
import com.biclioteca.domain.model.Libro;
import com.biclioteca.domain.repository.LibroRepository;

@Service
public class BuscarLibrosService implements IBuscarLibrosUseCase {

    private final LibroRepository libroRepository;

    public BuscarLibrosService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public List<Libro> ejecutar(BuscarLibrosQuery query) {
        String texto = query.texto() == null ? "" : query.texto().trim();
        return libroRepository.search(texto);
    }
}