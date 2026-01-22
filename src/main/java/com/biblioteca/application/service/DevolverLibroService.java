package com.biblioteca.application.service;

import org.springframework.stereotype.Service;

import com.biblioteca.application.usecase.DevolverLibroUseCaseImpl;
import com.biblioteca.domain.repository.ILibroRepository;
import com.biblioteca.domain.repository.IPrestamoRepository;

@Service
public class DevolverLibroService extends DevolverLibroUseCaseImpl {
  public DevolverLibroService(ILibroRepository libroRepository, IPrestamoRepository prestamoRepository) {
    super(libroRepository, prestamoRepository);
  }
}
