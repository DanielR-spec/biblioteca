package com.biblioteca.domain.repository;


import java.util.List;
import java.util.Optional;

import com.biblioteca.domain.model.Usuario;

public interface IUsuarioRepository {
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Long id);
    List<Usuario> findAll();
    void deleteById(Long id);
}