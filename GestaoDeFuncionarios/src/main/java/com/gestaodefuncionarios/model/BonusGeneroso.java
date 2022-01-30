
package com.gestaodefuncionarios.model;

import java.time.LocalDate;

public class BonusGeneroso implements IMetodoCalculaBonus{

    @Override
    public Bonus calcular(Funcionario funcionario, LocalDate data) {
        if(!funcionario.isBonusNormal()) {
            var valorBonus = funcionario.getSalarioBase() * 0.15;
            
            return new Bonus("Bônus generoso", data, valorBonus);
        }
        return null;
    }
    
}
