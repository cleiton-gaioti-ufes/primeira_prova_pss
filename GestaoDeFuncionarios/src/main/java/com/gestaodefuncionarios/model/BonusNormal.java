
package com.gestaodefuncionarios.model;

import java.time.LocalDate;

public class BonusNormal implements IMetodoCalculaBonus {

    @Override
    public Bonus calcular(Funcionario funcionario, LocalDate data) {
        if(funcionario.isBonusNormal()) {
            var valorBonus = funcionario.getSalarioBase() * 0.05;
            
            return new Bonus("Bônus normal", data, valorBonus);
        }
        return null;
    }
    
}
