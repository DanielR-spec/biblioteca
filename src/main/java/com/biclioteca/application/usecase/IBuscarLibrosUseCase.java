package com.biclioteca.application.usecase;

import java.util.List;

import com.biclioteca.application.dto.BuscarLibrosQuery;
import com.biclioteca.domain.model.Libro;

public interface IBuscarLibrosUseCase {

    List<Libro> ejecutar(BuscarLibrosQuery query);

    
}
