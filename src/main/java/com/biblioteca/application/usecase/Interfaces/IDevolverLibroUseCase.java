package com.biblioteca.application.usecase.Interfaces;

import com.biblioteca.application.dto.DevolucionResult;
import com.biblioteca.application.dto.DevolverLibroCommand;

public interface IDevolverLibroUseCase {

    DevolucionResult ejecutar(DevolverLibroCommand command);

}
