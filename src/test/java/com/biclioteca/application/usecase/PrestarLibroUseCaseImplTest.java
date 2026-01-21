package com.biclioteca.application.usecase;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biclioteca.application.dto.PrestamoResult;
import com.biclioteca.application.dto.PrestarLibroCommand;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class PrestarLibroUseCaseImplTest {

    @Autowired
    PrestarLibroUseCaseImpl useCase;

    @Test
    void presta_libro_ok() {
        PrestamoResult result = useCase.ejecutar(
                new PrestarLibroCommand(1L, 1L, null)
        );

        assertTrue(result.prestado());
    }
}
