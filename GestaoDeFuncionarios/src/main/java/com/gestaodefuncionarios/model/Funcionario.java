
package com.gestaodefuncionarios.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Funcionario {
    private final String nome;
    private int idade;
    private CargoEnum cargo;
    private double salario;
    private final ArrayList<Bonus> bonusRecebidos;
    private int faltas;
    private final LocalDate admissao;
    private boolean funcionarioDoMês;
    private boolean auxilioTransporte;
    private boolean bonusNormal;

    public Funcionario(String nome, int idade, double salario, ArrayList<Bonus> bonus, 
            int faltas, LocalDate admissao, CargoEnum cargo, boolean bonusNormal, boolean auxilioTransporte) {
        this.nome = nome;
        this.idade = idade;
        this.salario = salario;
        this.bonusRecebidos = bonus;
        this.faltas = faltas;
        this.admissao = admissao;
        this.funcionarioDoMês = false;
        this.cargo = cargo;
        this.bonusNormal = bonusNormal;
        this.auxilioTransporte = auxilioTransporte;
    }
    
    public void calcularSalario(IMetodoCalculaBonus metodoCalculaBonus) {
        metodoCalculaBonus.calcular(this);
        
        this.bonusRecebidos.forEach(b -> {
            this.salario = salario + b.getValor();
        });
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public double getSalario() {
        return salario;
    }

    public int getFaltas() {
        return faltas;
    }

    public LocalDate getAdmissao() {
        return admissao;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }
    
    public void add(Bonus bonusRecebido) {
        this.bonusRecebidos.add(bonusRecebido);
    }

    public CargoEnum getCargo() {
        return cargo;
    }

    public ArrayList<Bonus> getBonusRecebidos() {
        return bonusRecebidos;
    }

    public boolean isFuncionarioDoMês() {
        return funcionarioDoMês;
    }

    public boolean isBonusNormal() {
        return bonusNormal;
    }

    public boolean hasAuxilioTransporte() {
        return auxilioTransporte;
    }
    
}
