package com.biblioteca.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.biblioteca.domain.model.Usuario;
import com.biblioteca.domain.repository.IUsuarioRepository;
import com.biblioteca.infrastructure.persistence.entity.UsuarioEntity;
import com.biblioteca.infrastructure.persistence.jpa.SpringDataUsuarioRepository;

@Repository
public class UsuarioJpaRepository implements IUsuarioRepository {

  private final SpringDataUsuarioRepository springDataUsuarioRepository;

  public UsuarioJpaRepository(SpringDataUsuarioRepository springDataUsuarioRepository) {
    this.springDataUsuarioRepository = springDataUsuarioRepository;
  }

  @Override
  public Usuario save(Usuario usuario) {
    UsuarioEntity entity = toEntity(usuario);
    UsuarioEntity saved = springDataUsuarioRepository.save(entity);
    return toDomain(saved);
  }

  @Override
  public Optional<Usuario> findById(Long id) {
    return springDataUsuarioRepository.findById(id).map(this::toDomain);
  }

  @Override
  public List<Usuario> findAll() {
    return springDataUsuarioRepository.findAll().stream().map(this::toDomain).toList();
  }

  @Override
  public void deleteById(Long id) {
    springDataUsuarioRepository.deleteById(id);
  }

  public Usuario toDomain(UsuarioEntity entity) {
    return new Usuario(entity.getId(), entity.getNombre(), entity.getEmail(), entity.getRol());
  }

  public UsuarioEntity toEntity(Usuario domain) {
    UsuarioEntity e = new UsuarioEntity();
    e.setId(domain.getId());
    e.setNombre(domain.getNombre());
    e.setEmail(domain.getEmail());
    e.setRol(domain.getRol());
    return e;
  }
}
