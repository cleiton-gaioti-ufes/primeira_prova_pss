package com.gestaodefuncionarios.collection;

import com.gestaodefuncionarios.dao.BonusDAO;
import com.gestaodefuncionarios.model.Bonus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BonusCollection {

    private final BonusDAO bonusDAO;
    private final List<Bonus> bonusRecebidos;

    public BonusCollection(List<Bonus> bonus) {
        this.bonusRecebidos = bonus;
        this.bonusDAO = new BonusDAO();
    }

    public BonusCollection() {
        this(new ArrayList<>());
    }

    public void addBonus(Bonus bonus) {
        if (bonus != null && bonus.getValor() > 0) {
            this.bonusRecebidos.add(bonus);
        }
    }

    public List<Bonus> getBonusRecebidos() {
        return this.bonusRecebidos;
    }

    public double calcularTotalBonus() {
        var soma = 0.0;

        soma = bonusRecebidos.stream().map(b -> b.getValor()).reduce(soma, (accumulator, _item) -> accumulator + _item);

        return soma;
    }

    public void salvarBonusRecebidos(int idFuncionario) throws SQLException {
        for(Bonus b : this.bonusRecebidos) {
            bonusDAO.insert(idFuncionario, b);
        }
    }
}
