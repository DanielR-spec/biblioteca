package com.biclioteca.infrastructure.rest.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.biclioteca.application.usecase.PrestarLibroUseCaseImpl;

@WebMvcTest(PrestamoController.class)
public class PrestamoControllerTest {
     @Autowired
    MockMvc mockMvc;

    @Mock
    PrestarLibroUseCaseImpl useCase;

    @Test
    void prestar_libro_200() throws Exception {
        mockMvc.perform(post("/prestar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"libroId":1,"usuarioId":1}
                        """))
                .andExpect(status().isOk());
    }
}
