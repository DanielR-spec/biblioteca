package com.biblioteca.application.usecase;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.biblioteca.application.dto.PrestamoResult;
import com.biblioteca.application.dto.PrestarLibroCommand;
import com.biblioteca.application.usecase.Interfaces.IPrestarLibroUseCase;
import com.biblioteca.domain.exception.LibroNoEncontradoException;
import com.biblioteca.domain.model.Libro;
import com.biblioteca.domain.model.Prestamo;
import com.biblioteca.domain.repository.ILibroRepository;
import com.biblioteca.domain.repository.IPrestamoRepository;
import com.biblioteca.domain.repository.IUsuarioRepository;

import jakarta.transaction.Transactional;

public class PrestarLibroUseCaseImpl implements IPrestarLibroUseCase {

  private final ILibroRepository libroRepository;
  private final IUsuarioRepository usuarioRepository;
  private final IPrestamoRepository prestamoRepository;

  public PrestarLibroUseCaseImpl(ILibroRepository libroRepository, IUsuarioRepository usuarioRepository, IPrestamoRepository prestamoRepository) {
    this.libroRepository = libroRepository;
    this.usuarioRepository = usuarioRepository;
    this.prestamoRepository = prestamoRepository;
  }

  @Override
  @Transactional
  public PrestamoResult ejecutar(PrestarLibroCommand command) {
    if (command == null || command.libroId() == null || command.usuarioId() == null) {
      throw new IllegalArgumentException("Command invalido");
    }

    usuarioRepository.findById(command.usuarioId()).orElseThrow(() -> new IllegalArgumentException("Usuario no existe"));

    Libro libro = libroRepository.findById(command.libroId())
        .orElseThrow(() -> new LibroNoEncontradoException(command.libroId()));

    prestamoRepository.findActivoByLibroId(libro.getId()).ifPresent(p -> { throw new IllegalStateException("Libro ya prestado"); });

    libro.prestar();
    libroRepository.save(libro);

    Prestamo prestamo = new Prestamo(null, libro.getId(), command.usuarioId(), LocalDate.now(), null);
    Prestamo saved = prestamoRepository.save(prestamo);

    return new PrestamoResult(saved.getId(), saved.getLibroId(), saved.getUsuarioId(), saved.getFechaPrestamo());
  }
}