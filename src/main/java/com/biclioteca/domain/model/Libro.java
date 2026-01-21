package com.biclioteca.domain.model;

import com.biclioteca.domain.model.valueobjects.ISBN;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Libro {
    
    
    public Libro(Long id, String titulo, String autor, ISBN isbn, boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponible = disponible;
    }

    private Long id;
    private String titulo;
    private String autor;
    private ISBN isbn;
    private boolean disponible;

    public void prestar(){

    }

    public void devolver(){

    }

    public boolean isValid(){
        return true;
    }
}
