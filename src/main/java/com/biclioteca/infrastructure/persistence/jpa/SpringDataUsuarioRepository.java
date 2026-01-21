package com.biclioteca.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biclioteca.infrastructure.persistence.entity.UsuarioEntity;

public interface SpringDataUsuarioRepository extends JpaRepository<UsuarioEntity, Long> { }
