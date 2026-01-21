package com.biclioteca.application.usecase;

import com.biclioteca.application.dto.DevolucionResult;
import com.biclioteca.application.dto.DevolverLibroCommand;

public interface DevolverLibroUseCaseImpl {
    DevolucionResult ejecutar(DevolverLibroCommand command);
}
