package com.biblioteca.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.infrastructure.persistence.entity.UsuarioEntity;

public interface SpringDataUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {}
