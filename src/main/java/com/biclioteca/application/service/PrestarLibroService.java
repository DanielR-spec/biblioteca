package com.biclioteca.application.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.biclioteca.application.dto.PrestamoResult;
import com.biclioteca.application.dto.PrestarLibroCommand;
import com.biclioteca.application.usecase.IPrestarLibroUseCase;
import com.biclioteca.domain.exception.LibroNoEncontradoException;
import com.biclioteca.domain.model.Libro;
import com.biclioteca.domain.model.Prestamo;
import com.biclioteca.domain.repository.LibroRepository;
import com.biclioteca.domain.repository.PrestamoRepository;
import com.biclioteca.domain.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class PrestarLibroService implements IPrestarLibroUseCase {

    private final LibroRepository libroRepository;
    private final UsuarioRepository usuarioRepository;
    private final PrestamoRepository prestamoRepository;

    public PrestarLibroService(LibroRepository libroRepository,
                              UsuarioRepository usuarioRepository,
                              PrestamoRepository prestamoRepository) {
        this.libroRepository = libroRepository;
        this.usuarioRepository = usuarioRepository;
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    @Transactional
    public PrestamoResult ejecutar(PrestarLibroCommand command) {
        var libro = libroRepository.findById(command.libroId())
                .orElseThrow(() -> new LibroNoEncontradoException(command.libroId()));

        // Valida usuario existente (si no existe, IllegalArgumentException simple)
        usuarioRepository.findById(command.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado id=" + command.usuarioId()));

        // Reglas de negocio en dominio
        libro.prestar();
        libroRepository.save(libro);

        var fecha = command.fechaPrestamo() != null ? command.fechaPrestamo() : LocalDate.now();
        var prestamo = new Prestamo(null, libro.getId(), command.usuarioId(), fecha, null);

        var saved = prestamoRepository.save(prestamo);

        return new PrestamoResult(saved.getId(), saved.getLibroId(), saved.getUsuarioId(),
                saved.getFechaPrestamo(), saved.getFechaDevolucion());
    }
}
