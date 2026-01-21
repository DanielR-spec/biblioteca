package com.biclioteca.domain.repository;


import java.util.List;
import java.util.Optional;

import com.biclioteca.domain.model.Usuario;

public interface IUsuarioRepository {
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario);
    List<Usuario> findAll();
    void deleteById(Long id);
}