package com.gestaodefuncionarios.model;

import com.gestaodefuncionarios.model.enums.Graduacao;
import java.time.LocalDate;

public class BonusPorGraduacao implements IMetodoCalculaBonus {

    @Override
    public Bonus calcular(Funcionario funcionario, LocalDate data) {
        var valorBonus = 0.0;

        if (funcionario.getGraduacaoString().equals(Graduacao.EnsinoFundamental.getDescricao())) {
            valorBonus = funcionario.getSalarioBase() * 0.01;
        } else if (funcionario.getGraduacaoString().equals(Graduacao.EnsinoMedio.getDescricao())) {
            valorBonus = funcionario.getSalarioBase() * 0.03;
        } else if (funcionario.getGraduacaoString().equals(Graduacao.EnsinoSuperior.getDescricao())) {
            valorBonus = funcionario.getSalarioBase() * 0.05;
        } else if (funcionario.getGraduacaoString().equals(Graduacao.Mestrado.getDescricao())) {
            valorBonus = funcionario.getSalarioBase() * 0.07;
        } else if (funcionario.getGraduacaoString().equals(Graduacao.Doutorado.getDescricao())) {
            valorBonus = funcionario.getSalarioBase() * 0.09;
        }

        return new Bonus("Funcionário do mês", data, valorBonus);
        
    }

}
