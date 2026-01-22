package com.biblioteca.infrastructure.rest.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(title = "Biblioteca API", version = "1.0")
)
@Configuration
public class OpenApiConfig {}
