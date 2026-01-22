package com.biblioteca.application.service;

import org.springframework.stereotype.Service;

import com.biblioteca.application.usecase.BuscarLibrosUseCaseImpl;
import com.biblioteca.domain.repository.ILibroRepository;

@Service
public class BuscarLibrosService extends BuscarLibrosUseCaseImpl {
  public BuscarLibrosService(ILibroRepository libroRepository) {
    super(libroRepository);
  }
}