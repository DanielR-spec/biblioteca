package com.biclioteca.domain.model.valueobjects;

import java.util.Objects;


public final class ISBN {
    private final String valor;

    public ISBN(String valor) {
        if (valor == null) {
            throw new IllegalArgumentException("ISBN no puede ser null");
        }
        String normalized = valor.replace("-", "").trim();
        if (!isValid(normalized)) {
            throw new IllegalArgumentException("ISBN inválido (debe tener 13 dígitos): " + valor);
        }
        this.valor = normalized;
    }

    public String getValor() {
        return valor;
    }

    public static boolean isValid(String valor) {
        if (valor == null) return false;
        return valor.matches("^\\d{13}$");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ISBN isbn)) return false;
        return Objects.equals(valor, isbn.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return valor;
    }
}
