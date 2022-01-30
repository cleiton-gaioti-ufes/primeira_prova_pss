
package com.gestaodefuncionarios.model;

import java.time.LocalDate;


public class BonusAuxilioTransporte implements IMetodoCalculaBonus {

    @Override
    public Bonus calcular(Funcionario funcionario, LocalDate data) {
        if(funcionario.hasAuxilioTransporte()) {
            var valorBonus = 0.0;
            var salario = funcionario.getSalarioBase();
            
            if(salario <= 1500) {
                valorBonus = salario * 0.10;
            } else if(salario > 1500 && salario <= 2400) {
                valorBonus = salario * 0.05;
            } else if(salario > 2400 && salario <= 3000) {
                valorBonus = salario * 0.03;
            } else {
                valorBonus = salario * 0.01;
            }
            
            return new Bonus("Auxílio transporte", data, valorBonus);
        }
        return null;
    }
    
}
