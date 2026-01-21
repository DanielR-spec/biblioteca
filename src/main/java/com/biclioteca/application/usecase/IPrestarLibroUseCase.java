package com.biclioteca.application.usecase;

import com.biclioteca.application.dto.PrestamoResult;
import com.biclioteca.application.dto.PrestarLibroCommand;

public interface IPrestarLibroUseCase {

        PrestamoResult ejecutar(PrestarLibroCommand command);

}    

