package com.gestaodefuncionarios.model;

import java.time.LocalDate;

public final class HistoricoBonus {
    
    private LocalDate data;
    private String cargo;
    private String tipoBonus;
    private double valor;

    public HistoricoBonus(LocalDate data, String cargo, String tipoBonus, double valor) {
        setData(data);
        setCargo(cargo);
        setTipoBonus(tipoBonus);
        setValor(valor);
    }

    private final void setData(LocalDate data) {
        if(data == null) {

            throw new RuntimeException("Data inválida!");
        } else {

            this.data = data;
        }
    }

    public final LocalDate getData() {
        return this.data;
    }

    private final void setCargo(String cargo) {
        if(cargo.isBlank() || cargo.isEmpty()) {

            throw new RuntimeException("Cargo inválido!");
        } else {
            this.cargo = cargo;
        }
    }

    public final String getCargo() {
        return this.cargo;
    }

    private final void setTipoBonus(String tipoBonus) {
        if(tipoBonus.isBlank() || tipoBonus.isEmpty()) {

            throw new RuntimeException("Bonus inválido!");
        } else {
            this.tipoBonus = tipoBonus;
        }
    }

    public final String getTipoBonus() {
        return this.tipoBonus;
    }

    private final void setValor(double valor) {
        if(valor <= 0) {
            throw new RuntimeException("Valor inválido!");
        } else {
            this.valor = valor;
        }
    }

    public final double getValor() {
        return this.valor;
    }
}
