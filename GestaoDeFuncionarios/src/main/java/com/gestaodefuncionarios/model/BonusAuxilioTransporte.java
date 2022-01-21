
package com.gestaodefuncionarios.model;

import java.time.LocalDate;

public class BonusAuxilioTransporte implements IMetodoCalculaBonus {

    @Override
    public void calcular(Funcionario funcionario) {
        if(funcionario.hasAuxilioTransporte()) {
            var valorBonus = 0.0;
            var salario = funcionario.getSalario();
            
            if(salario <= 1500) {
                valorBonus = salario * 0.10;
            } else if(salario > 1500 && salario <= 2400) {
                valorBonus = salario * 0.05;
            } else if(salario > 2400 && salario <= 3000) {
                valorBonus = salario * 0.03;
            } else {
                valorBonus = salario * 0.01;
            }
            
            funcionario.getBonusRecebidos().add(new Bonus("Funcionário do mês", LocalDate.now(), valorBonus));
        }
    }
    
}
