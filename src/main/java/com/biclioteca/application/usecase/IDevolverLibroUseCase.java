package com.biclioteca.application.usecase;

import com.biclioteca.application.dto.DevolverLibroCommand;
import com.biclioteca.application.dto.PrestamoResult;

public interface IDevolverLibroUseCase {

    PrestamoResult ejecutar(DevolverLibroCommand command);

}
