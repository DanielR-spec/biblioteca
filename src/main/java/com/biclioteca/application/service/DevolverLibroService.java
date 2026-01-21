package com.biclioteca.application.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.biclioteca.application.dto.DevolucionResult;
import com.biclioteca.application.dto.DevolverLibroCommand;
import com.biclioteca.application.dto.PrestamoResult;
import com.biclioteca.application.usecase.IDevolverLibroUseCase;
import com.biclioteca.domain.exception.LibroNoEncontradoException;
import com.biclioteca.domain.model.Libro;
import com.biclioteca.domain.model.Prestamo;
import com.biclioteca.domain.repository.LibroRepository;
import com.biclioteca.domain.repository.PrestamoRepository;

import jakarta.transaction.Transactional;

@Service
public class DevolverLibroService implements IDevolverLibroUseCase {

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;

    public DevolverLibroService(PrestamoRepository prestamoRepository, LibroRepository libroRepository) {
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
    }

    @Override
    @Transactional
    public PrestamoResult ejecutar(DevolverLibroCommand command) {
        Prestamo prestamo = prestamoRepository.findById(command.prestamoId())
                .orElseThrow(() -> new IllegalArgumentException("Préstamo no encontrado id=" + command.prestamoId()));

        var libro = libroRepository.findById(prestamo.getLibroId())
                .orElseThrow(() -> new LibroNoEncontradoException(prestamo.getLibroId()));

        var fecha = command.fechaDevolucion() != null ? command.fechaDevolucion() : LocalDate.now();

        prestamo.devolver(fecha);
        var savedPrestamo = prestamoRepository.save(prestamo);

        libro.devolver();
        libroRepository.save(libro);

        return new PrestamoResult(savedPrestamo.getId(), savedPrestamo.getLibroId(), savedPrestamo.getUsuarioId(),
                savedPrestamo.getFechaPrestamo(), savedPrestamo.getFechaDevolucion());
    }
}