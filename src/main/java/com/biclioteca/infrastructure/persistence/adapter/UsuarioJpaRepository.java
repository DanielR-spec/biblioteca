package com.biclioteca.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.biclioteca.domain.model.Usuario;
import com.biclioteca.domain.repository.IUsuarioRepository;
import com.biclioteca.infrastructure.persistence.entity.UsuarioEntity;
import com.biclioteca.infrastructure.persistence.jpa.SpringDataUsuarioRepository;

@Repository
public class UsuarioJpaRepository implements IUsuarioRepository {

    private final SpringDataUsuarioRepository jpa;

    public UsuarioJpaRepository(SpringDataUsuarioRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public Usuario save(Usuario usuario) {
        var saved = jpa.save(toEntity(usuario));
        return toDomain(saved);
    }

    @Override
    public List<Usuario> findAll() {
        return jpa.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }

    private Usuario toDomain(UsuarioEntity e) {
        return new Usuario(e.getId(), e.getNombre(), e.getEmail(), e.getRol());
    }

    private UsuarioEntity toEntity(Usuario d) {
        var e = new UsuarioEntity();
        e.setId(d.getId());
        e.setNombre(d.getNombre());
        e.setEmail(d.getEmail());
        e.setRol(d.getRol());
        return e;
    }
}
