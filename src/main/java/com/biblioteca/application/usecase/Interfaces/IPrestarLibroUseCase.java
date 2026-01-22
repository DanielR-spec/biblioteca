package com.biblioteca.application.usecase.Interfaces;

import com.biblioteca.application.dto.PrestamoResult;
import com.biblioteca.application.dto.PrestarLibroCommand;

public interface IPrestarLibroUseCase {

        PrestamoResult ejecutar(PrestarLibroCommand command);

}    

