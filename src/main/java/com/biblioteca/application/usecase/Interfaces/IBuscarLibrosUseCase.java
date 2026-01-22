package com.biblioteca.application.usecase.Interfaces;


import com.biblioteca.application.dto.BuscarLibrosQuery;
import com.biblioteca.application.dto.ListadoLibrosResult;

public interface IBuscarLibrosUseCase {

    ListadoLibrosResult  ejecutar(BuscarLibrosQuery query);

}
