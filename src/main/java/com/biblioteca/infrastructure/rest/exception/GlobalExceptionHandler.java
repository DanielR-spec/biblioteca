package com.biblioteca.infrastructure.rest.exception;

import java.time.Instant;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.biblioteca.domain.exception.LibroNoDisponibleException;
import com.biblioteca.domain.exception.LibroNoEncontradoException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(LibroNoEncontradoException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ProblemDetail handleLibroNoEncontrado(LibroNoEncontradoException ex) {
    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    pd.setTitle("Recurso no encontrado");
    pd.setProperty("timestamp", Instant.now().toString());
    return pd;
  }

  @ExceptionHandler(LibroNoDisponibleException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ProblemDetail handleLibroNoDisponible(LibroNoDisponibleException ex) {
    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    pd.setTitle("Conflicto de negocio");
    pd.setProperty("timestamp", Instant.now().toString());
    return pd;
  }

  @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class, IllegalArgumentException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ProblemDetail handleBadRequest(Exception ex) {
    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    pd.setTitle("Solicitud inválida");
    pd.setProperty("timestamp", Instant.now().toString());
    return pd;
  }

  @ExceptionHandler(IllegalStateException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ProblemDetail handleIllegalState(IllegalStateException ex) {
    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    pd.setTitle("Estado inválido");
    pd.setProperty("timestamp", Instant.now().toString());
    return pd;
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ProblemDetail handleGeneric(Exception ex) {
    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Error inesperado");
    pd.setTitle("Error interno");
    pd.setProperty("timestamp", Instant.now().toString());
    return pd;
  }
}