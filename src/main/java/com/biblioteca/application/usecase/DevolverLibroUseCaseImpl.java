package com.biblioteca.application.usecase;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.biblioteca.application.dto.DevolucionResult;
import com.biblioteca.application.dto.DevolverLibroCommand;
import com.biblioteca.application.dto.PrestamoResult;
import com.biblioteca.application.usecase.Interfaces.IDevolverLibroUseCase;
import com.biblioteca.domain.exception.LibroNoEncontradoException;
import com.biblioteca.domain.model.Libro;
import com.biblioteca.domain.model.Prestamo;
import com.biblioteca.domain.repository.ILibroRepository;
import com.biblioteca.domain.repository.IPrestamoRepository;

import jakarta.transaction.Transactional;

public class DevolverLibroUseCaseImpl implements IDevolverLibroUseCase {

  private final ILibroRepository libroRepository;
  private final IPrestamoRepository prestamoRepository;

  public DevolverLibroUseCaseImpl(ILibroRepository libroRepository, IPrestamoRepository prestamoRepository) {
    this.libroRepository = libroRepository;
    this.prestamoRepository = prestamoRepository;
  }

  @Override
  @Transactional
  public DevolucionResult ejecutar(DevolverLibroCommand command) {
    if (command == null || command.libroId() == null) {
      throw new IllegalArgumentException("Command invalido");
    }

    Libro libro = libroRepository.findById(command.libroId())
        .orElseThrow(() -> new LibroNoEncontradoException(command.libroId()));

    Prestamo activo = prestamoRepository.findActivoByLibroId(libro.getId())
        .orElseThrow(() -> new IllegalStateException("No existe prestamo activo"));

    Prestamo devuelto = new Prestamo(activo.getId(), activo.getLibroId(), activo.getUsuarioId(), activo.getFechaPrestamo(), LocalDate.now());
    Prestamo saved = prestamoRepository.save(devuelto);

    libro.devolver();
    libroRepository.save(libro);

    return new DevolucionResult(saved.getId(), saved.getLibroId(), saved.getFechaDevolucion());
  }
}