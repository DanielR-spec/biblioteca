package com.biblioteca.application.service;

import org.springframework.stereotype.Service;

import com.biblioteca.application.usecase.PrestarLibroUseCaseImpl;
import com.biblioteca.domain.repository.ILibroRepository;
import com.biblioteca.domain.repository.IPrestamoRepository;
import com.biblioteca.domain.repository.IUsuarioRepository;

@Service
public class PrestarLibroService extends PrestarLibroUseCaseImpl {
  public PrestarLibroService(ILibroRepository libroRepository, IUsuarioRepository usuarioRepository, IPrestamoRepository prestamoRepository) {
    super(libroRepository, usuarioRepository, prestamoRepository);
  }
}