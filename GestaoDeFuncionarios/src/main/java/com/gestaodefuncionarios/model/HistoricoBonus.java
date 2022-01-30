package com.gestaodefuncionarios.model;

import java.time.LocalDate;

public class HistoricoBonus {
    
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

    private void setData(LocalDate data) {
        if(data == null) {

            throw new RuntimeException("Data inválida!");
        } else {

            this.data = data;
        }
    }

    public LocalDate getData() {
        return this.data;
    }

    private void setCargo(String cargo) {
        if(cargo.isBlank() || cargo.isEmpty()) {

            throw new RuntimeException("Cargo inválido!");
        } else {
            this.cargo = cargo;
        }
    }

    public String getCargo() {
        return this.cargo;
    }

    private void setTipoBonus(String tipoBonus) {
        if(tipoBonus.isBlank() || tipoBonus.isEmpty()) {

            throw new RuntimeException("Bonus inválido!");
        } else {
            this.tipoBonus = tipoBonus;
        }
    }

    public String getTipoBonus() {
        return this.tipoBonus;
    }

    private void setValor(double valor) {
        if(valor <= 0) {
            throw new RuntimeException("Valor inválido!");
        } else {
            this.valor = valor;
        }
    }

    public double getValor() {
        return this.valor;
    }
}
