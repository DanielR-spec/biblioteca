package com.biclioteca.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.biclioteca.domain.model.Prestamo;

@Repository
public class PrestamoRepository implements IPrestamoRepository {

    @Override
    public Prestamo save(Prestamo prestamo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Optional<Prestamo> findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Optional<Prestamo> findActivoByLibroId(Long libroId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findActivoByLibroId'");
    }

    @Override
    public List<Prestamo> findByUsuarioId(Long usuarioId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUsuarioId'");
    }
    
}
