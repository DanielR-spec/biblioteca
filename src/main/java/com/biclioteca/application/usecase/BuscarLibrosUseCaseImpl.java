package com.biclioteca.application.usecase;

import com.biclioteca.application.dto.BuscarLibrosQuery;
import com.biclioteca.application.dto.ListadoLibrosResult;

public interface BuscarLibrosUseCaseImpl {
    ListadoLibrosResult ejecutar(BuscarLibrosQuery query);
}
