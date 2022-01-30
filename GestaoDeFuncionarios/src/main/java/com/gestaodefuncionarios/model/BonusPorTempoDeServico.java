package com.gestaodefuncionarios.model;

import java.time.LocalDate;
import java.time.Period;

public class BonusPorTempoDeServico implements IMetodoCalculaBonus {

    @Override
    public Bonus calcular(Funcionario funcionario, LocalDate data) {
        var anos = Period.between(funcionario.getAdmissao(), LocalDate.now()).getYears();
        var valorBonus = 0.0;

        if (anos > 0 && anos <= 5) {
            valorBonus = funcionario.getSalarioBase() * 0.02;
        } else if (anos > 5 && anos <= 10) {
            valorBonus = funcionario.getSalarioBase() * 0.03;
        } else if (anos > 10 && anos <= 15) {
            valorBonus = funcionario.getSalarioBase() * 0.08;
        } else if (anos > 15 && anos <= 20) {
            valorBonus = funcionario.getSalarioBase() * 0.1;
        } else if (anos > 20) {
            valorBonus = funcionario.getSalarioBase() * 0.15;
        }

        return new Bonus("Bônus por tempo de serviço", data, valorBonus);
    }

}
