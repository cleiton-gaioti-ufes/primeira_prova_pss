
package com.gestaodefuncionarios.model;

import java.time.LocalDate;

public class BonusPorAssiduidade implements IMetodoCalculaBonus {

    @Override
    public void calcular(Funcionario funcionario) {
        int nroFaltas = funcionario.getFaltas();
        double valorBonus;
        
        if(nroFaltas == 0) {
            valorBonus = funcionario.getSalario() * 0.1;
        } else if(nroFaltas > 1 && nroFaltas <= 3) {
            valorBonus = funcionario.getSalario() * 0.05;
        } else {
            valorBonus = funcionario.getSalario() * 0.01;
        }
        
        funcionario.add(new Bonus("Bônus por assiduidade", LocalDate.now(), valorBonus));
    }
    
}
