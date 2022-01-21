
package com.gestaodefuncionarios.model;

import java.time.LocalDate;

public class BonusDeFuncionarioDoMes implements IMetodoCalculaBonus {

    @Override
    public void calcular(Funcionario funcionario) {
        if(funcionario.isFuncionarioDoMês()) {
            var valorBonus = funcionario.getSalario() * 0.15;
            
            funcionario.getBonusRecebidos().add(new Bonus("Funcionário do mês", LocalDate.now(), valorBonus));
        }
    }
    
}
