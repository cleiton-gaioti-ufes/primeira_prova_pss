package com.gestaodefuncionarios.collection;

import com.gestaodefuncionarios.model.Funcionario;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioCollection {

    private final List<Funcionario> funcionarios;

    public FuncionarioCollection(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public FuncionarioCollection() {
        this(new ArrayList<>());
    }

    public void addFuncionario(Funcionario funcionario) {
        if (funcionarios.contains(funcionario)) {
            throw new RuntimeException("Funcionário já cadastrado");
        } else if (funcionario == null) {
            throw new RuntimeException("Funcionário nulo");
        } else {
            this.funcionarios.add(funcionario);
        }
    }

    public void delFuncionario(Funcionario funcionario) {
        if (funcionario == null) {
            throw new RuntimeException("Funcionário nulo");
        } else if (!this.funcionarios.contains(funcionario)) {
            throw new RuntimeException("Funcionario não cadastrado");
        } else {
            this.funcionarios.remove(funcionario);
        }
    }

    public List<Funcionario> getFuncionarios() {
        return this.funcionarios;
    }
    
}
