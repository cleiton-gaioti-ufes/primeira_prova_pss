
package com.gestaodefuncionarios.model;

import java.time.LocalDate;

public interface IMetodoCalculaBonus {
    public Bonus calcular(Funcionario funcionario, LocalDate data);
}
