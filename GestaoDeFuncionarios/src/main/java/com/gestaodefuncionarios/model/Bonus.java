
package com.gestaodefuncionarios.model;

import java.time.LocalDate;

public class Bonus {
    private final String nome;
    private final LocalDate data;
    private final double valor;

    public Bonus(String nome, LocalDate data, double valor) {
        this.nome = nome;
        this.data = data;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }
}
