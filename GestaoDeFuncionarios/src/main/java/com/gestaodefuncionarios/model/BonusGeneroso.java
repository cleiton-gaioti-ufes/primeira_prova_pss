
package com.gestaodefuncionarios.model;

import java.time.LocalDate;

public class BonusGeneroso implements IMetodoCalculaBonus{

    @Override
    public void calcular(Funcionario funcionario) {
        if(!funcionario.isBonusNormal()) {
            var valorBonus = funcionario.getSalario() * 0.15;
            
            funcionario.getBonusRecebidos().add(new Bonus("Bônus generoso", LocalDate.now(), valorBonus));
        }
    }
    
}
