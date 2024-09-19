package org.example.model;

public enum Curso {
    ADS,
    CCMP,
    ECMP,
    OUTROS;


    public String getNome() {
        return this.name(); // Retorna o nome da constante como String
    }
}
