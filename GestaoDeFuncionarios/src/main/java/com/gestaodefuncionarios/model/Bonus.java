package com.gestaodefuncionarios.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Bonus implements Serializable {

    private final int id;
    private final String nome;
    private final LocalDate data;
    private final double valor;

    public Bonus(int id, String nome, LocalDate data, double valor) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.valor = valor;
    }

    public Bonus(String nome, LocalDate data, double valor) {
        this(-1, nome, data, valor);
    }

    public int getId() {
        return id;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.nome);
        hash = 41 * hash + Objects.hashCode(this.data);
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bonus other = (Bonus) obj;
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }
}
