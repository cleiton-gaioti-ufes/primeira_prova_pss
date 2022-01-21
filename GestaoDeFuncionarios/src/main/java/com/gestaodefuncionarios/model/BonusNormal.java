
package com.gestaodefuncionarios.model;

import java.time.LocalDate;

public class BonusNormal implements IMetodoCalculaBonus {

    @Override
    public void calcular(Funcionario funcionario) {
        if(funcionario.isBonusNormal()) {
            var valorBonus = funcionario.getSalario() * 0.05;
            
            funcionario.getBonusRecebidos().add(new Bonus("Bônus normal", LocalDate.now(), valorBonus));
        }
    }
    
}
