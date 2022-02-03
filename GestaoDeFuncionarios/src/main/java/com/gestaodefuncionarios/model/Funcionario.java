package com.gestaodefuncionarios.model;

import com.gestaodefuncionarios.model.enums.Graduacao;
import com.gestaodefuncionarios.model.enums.Cargo;
import com.gestaodefuncionarios.collection.BonusCollection;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public final class Funcionario implements Serializable {

    private int id;
    private String nome;
    private int idade;
    private int cargo;
    private double salarioBase;
    private int faltas;
    private LocalDate admissao;
    private int graduacao;
    private boolean auxilioTransporte;
    private boolean bonusNormal;

    public Funcionario(int id, String nome, int idade, double salarioBase, int faltas, String admissao, Cargo cargo,
            boolean bonusNormal, Graduacao graduacao, boolean auxilioTransporte) {
                
        this.id = id;
        setNome(nome);
        setIdade(idade);
        setSalarioBase(salarioBase);
        setFaltas(faltas);
        setAdmissao(admissao);
        setGraduacao(graduacao.getCodigo());
        setCargo(cargo.getCodigo());
        setAuxilioTransporte(auxilioTransporte);
        setBonusNormal(bonusNormal);
    }

    public Funcionario(String nome, String idade, String salarioBase, String faltas, String admissao, Cargo cargo,
            boolean bonusNormal, Graduacao graduacao, boolean auxilioTransporte) {

        setNome(nome);
        setIdade(idade);
        setSalarioBase(salarioBase);
        setFaltas(faltas);
        setAdmissao(admissao);
        setGraduacao(graduacao.getCodigo());
        setCargo(cargo.getCodigo());
        setAuxilioTransporte(auxilioTransporte);
        setBonusNormal(bonusNormal);
    }

    public Funcionario(String nome, int idade, double salarioBase, int faltas, String admissao, Cargo cargo,
            boolean bonusNormal, Graduacao graduacao, boolean auxilioTransporte) {

        this(-1, nome, idade, salarioBase, faltas, admissao, cargo, bonusNormal, graduacao, auxilioTransporte);
    }

    private Bonus calcularBonus(IMetodoCalculaBonus metodoCalculaBonus, LocalDate data) {
        return metodoCalculaBonus.calcular(this, data);
    }

    public double calcularSalario(LocalDate data) throws SQLException {
        var bonusRecebidos = new BonusCollection();

        bonusRecebidos.addBonus(calcularBonus(new BonusAuxilioTransporte(), data));
        bonusRecebidos.addBonus(calcularBonus(new BonusNormal(), data));
        bonusRecebidos.addBonus(calcularBonus(new BonusGeneroso(), data));
        bonusRecebidos.addBonus(calcularBonus(new BonusPorAssiduidade(), data));
        bonusRecebidos.addBonus(calcularBonus(new BonusPorGraduacao(), data));
        bonusRecebidos.addBonus(calcularBonus(new BonusPorTempoDeServico(), data));

        bonusRecebidos.salvarBonusRecebidos(this.id);

        var salario = salarioBase + bonusRecebidos.calcularTotalBonus();

        return salario;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.isBlank() || nome.isEmpty()) {
            throw new RuntimeException("Nome em branco");
        } else if (nome.length() < 5) {
            throw new RuntimeException("Nome inválido. Mínimo de 5 caracteres");
        } else {
            this.nome = nome;
        }
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade < 16 || idade > 100) {
            throw new RuntimeException("Idade inválida");
        } else {
            this.idade = idade;
        }
    }

    public void setIdade(String idade) {
        try {

            setIdade(Integer.valueOf(idade));

        } catch (NumberFormatException ex) {

            throw new RuntimeException("Campo idade só aceita dígitos");

        }
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salario) {
        if (salario <= 0) {
            throw new RuntimeException("Salário inválido");
        } else {
            this.salarioBase = salario;
        }
    }

    public void setSalarioBase(String salario) {
        salario = salario.replace(".", "").replace(",", ".");

        try {

            setSalarioBase(Double.valueOf(salario));

        } catch (NumberFormatException ex) {

            throw new RuntimeException("Campo salário só aceita dígitos");

        }
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        if (faltas < 0 || faltas > 31) {
            throw new RuntimeException("Número de faltas inválido");
        } else {
            this.faltas = faltas;
        }
    }

    public void setFaltas(String faltas) {
        try {

            setFaltas(Integer.valueOf(faltas));

        } catch (NumberFormatException ex) {

            throw new RuntimeException("Campo faltas só aceita dígitos");

        }
    }

    public LocalDate getAdmissao() {
        return admissao;
    }

    public void setAdmissao(String admissao) {
        if (admissao.isBlank() || admissao.isEmpty()) {
            throw new RuntimeException("Informe uma data");
        } else {
            try {
                this.admissao = LocalDate.parse(admissao, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException ex) {
                throw new RuntimeException("Informe uma data válida!");
            }
        }
    }

    public String getCargoString() {
        return Cargo.toEnum(cargo).getDescricao();
    }

    public int getCargoId() {
        return this.cargo;
    }

    public void setCargo(int idCargo) {
        this.cargo = idCargo;
    }

    public String getGraduacaoString() {
        return Graduacao.toEnum(graduacao).getDescricao();
    }

    public int getGraduacaoId() {
        return this.graduacao;
    }

    public void setGraduacao(int graduacao) {
        this.graduacao = graduacao;
    }

    public boolean isBonusNormal() {
        return bonusNormal;
    }

    public void setBonusNormal(boolean isBonusNormal) {
        this.bonusNormal = isBonusNormal;
    }

    public boolean hasAuxilioTransporte() {
        return auxilioTransporte;
    }

    public void setAuxilioTransporte(boolean hasAuxilioTransporte) {
        this.auxilioTransporte = hasAuxilioTransporte;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + this.idade;
        hash = 29 * hash + Objects.hashCode(this.admissao);
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
        final Funcionario other = (Funcionario) obj;
        if (this.idade != other.idade) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.admissao, other.admissao)) {
            return false;
        }
        return true;
    }

}
