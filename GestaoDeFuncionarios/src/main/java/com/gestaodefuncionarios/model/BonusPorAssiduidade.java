
package com.gestaodefuncionarios.model;

import java.time.LocalDate;

public class BonusPorAssiduidade implements IMetodoCalculaBonus {

    @Override
    public Bonus calcular(Funcionario funcionario, LocalDate data) {
        int nroFaltas = funcionario.getFaltas();
        var valorBonus = 0.0;
        
        if(nroFaltas == 0) {
            valorBonus = funcionario.getSalarioBase() * 0.1;
        } else if(nroFaltas > 1 && nroFaltas <= 3) {
            valorBonus = funcionario.getSalarioBase() * 0.05;
        } else {
            valorBonus = funcionario.getSalarioBase() * 0.01;
        }
        
        return new Bonus("Bônus por assiduidade", data, valorBonus);
    }
    
}
