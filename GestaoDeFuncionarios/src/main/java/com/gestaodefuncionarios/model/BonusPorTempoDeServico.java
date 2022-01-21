
package com.gestaodefuncionarios.model;

import java.time.LocalDate;
import java.time.Period;

public class BonusPorTempoDeServico implements IMetodoCalculaBonus {
    
    @Override
    public void calcular(Funcionario funcionario) {
        var anos = Period.between(funcionario.getAdmissao(), LocalDate.now()).getYears();
        var valorBonus = 0.0;
        
        if(anos > 0 && anos <= 5) {
            valorBonus = funcionario.getSalario() * 0.02;
        } else if(anos > 5 && anos <= 10){
            valorBonus = funcionario.getSalario() * 0.03;
        } else if(anos > 10 && anos <= 15) {
            valorBonus = funcionario.getSalario() * 0.08;
        } else if(anos > 15 && anos <= 20) {
            valorBonus = funcionario.getSalario() * 0.1;
        } else if(anos > 20) {
            valorBonus = funcionario.getSalario() * 0.15;
        }
        
        funcionario.getBonusRecebidos().add(new Bonus("Bônus por tempo de serviço", LocalDate.now(), valorBonus));
    }
    
}
