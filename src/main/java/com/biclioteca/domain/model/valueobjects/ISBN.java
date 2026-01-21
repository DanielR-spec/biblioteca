package com.biclioteca.domain.model.valueobjects;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public final class ISBN {
    
    public ISBN(String valor, boolean isValid) {
        this.valor = valor;
        this.isValid = isValid;
    }
    private String valor;
    private boolean isValid;
}
