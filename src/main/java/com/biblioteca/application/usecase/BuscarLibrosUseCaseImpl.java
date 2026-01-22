package com.biblioteca.application.usecase;

import java.util.List;

import com.biblioteca.application.dto.BuscarLibrosQuery;
import com.biblioteca.application.dto.ListadoLibrosResult;
import com.biblioteca.application.usecase.Interfaces.IBuscarLibrosUseCase;
import com.biblioteca.domain.model.Libro;
import com.biblioteca.domain.repository.ILibroRepository;

public class BuscarLibrosUseCaseImpl implements IBuscarLibrosUseCase {

  private final ILibroRepository libroRepository;

  public BuscarLibrosUseCaseImpl(ILibroRepository libroRepository) {
    this.libroRepository = libroRepository;
  }

  @Override
  public ListadoLibrosResult ejecutar(BuscarLibrosQuery query) {
    String titulo = query != null ? query.titulo() : null;
    String autor = query != null ? query.autor() : null;
    String isbn = query != null ? query.isbn() : null;

    List<Libro> libros = libroRepository.search(titulo, autor, isbn);

    List<ListadoLibrosResult.LibroItem> items = libros.stream()
        .map(l -> new ListadoLibrosResult.LibroItem(
            l.getId(),
            l.getTitulo(),
            l.getAutor(),
            l.getIsbn().getValor(),
            l.isDisponible()
        ))
        .toList();

    return new ListadoLibrosResult(items);
  }
}