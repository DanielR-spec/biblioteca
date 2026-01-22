package com.biblioteca.infrastructure.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.domain.model.Usuario;
import com.biblioteca.domain.repository.IUsuarioRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

  private final IUsuarioRepository usuarioRepository;

  public UsuarioController(IUsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  public record UsuarioRequest(@NotBlank String nombre, @NotBlank @Email String email, @NotBlank String rol) {}

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Usuario create(@RequestBody @Valid UsuarioRequest request) {
    Usuario usuario = new Usuario(null, request.nombre(), request.email(), request.rol());
    return usuarioRepository.save(usuario);
  }

  @GetMapping
  public List<Usuario> list() {
    return usuarioRepository.findAll();
  }

  @GetMapping("/{id}")
  public Usuario get(@PathVariable Long id) {
    return usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    usuarioRepository.deleteById(id);
  }
}