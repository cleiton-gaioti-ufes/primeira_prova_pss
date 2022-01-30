package com.gestaodefuncionarios.dao;

import com.gestaodefuncionarios.collection.BonusCollection;
import com.gestaodefuncionarios.factory.ConnectionSQLite;
import com.gestaodefuncionarios.model.Bonus;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class BonusDAO {

    public static void createTableBonus() throws SQLException {
        var query = "CREATE TABLE IF NOT EXISTS funcionario_bonus("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "id_funcionario INTEGER NOT NULL REFERENCES funcionario (id), "
                + "nome VARCHAR NOT NULL, "
                + "data DATE NOT NULL, "
                + "valor DOUBLE PRECISION NOT NULL"
                + ")";

        try (
            Connection conn = ConnectionSQLite.connect(); 
            Statement stmt = conn.createStatement()
        ) {
            stmt.execute(query);
            
            stmt.close();
            conn.close();
        }
    }

    public void insert(int idFuncionario, Bonus bonus) throws SQLException {
        var query = "INSERT INTO funcionario_bonus(id_funcionario, nome, data, valor) VALUES(?, ?, ?, ?)";

        try (
            Connection conn = ConnectionSQLite.connect(); 
            PreparedStatement ps = conn.prepareStatement(query)
        ) {
            
            ps.setInt(1, idFuncionario);
            ps.setString(2, bonus.getNome());
            ps.setDate(3, Date.valueOf(bonus.getData()));
            ps.setDouble(4, bonus.getValor());
            
            ps.execute();
            
            ps.close();
            conn.close();
        }
    }

    public void deleteBonusByData(LocalDate data) throws SQLException {
        var query = "DELETE FROM funcionario_bonus WHERE data = ?";

        try (
            Connection conn = ConnectionSQLite.connect();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setDate(1, Date.valueOf(data));
            ps.executeUpdate();

            ps.close();
            conn.close();
        }
    }

    public BonusCollection getBonusFuncionario(int id_funcionario) throws SQLException {
        BonusCollection bonusRecebidos = new BonusCollection();

        var query = "SELECT * FROM funcionario_bonus WHERE id_funcionario = ?";

        try (
            Connection conn = ConnectionSQLite.connect(); 
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, id_funcionario);
            
            ResultSet r = ps.executeQuery();
            
            while (r.next()) {
                Bonus bonus = new Bonus(
                        r.getInt("id"),
                        r.getString("nome"),
                        LocalDate.parse(r.getDate("data").toString()),
                        r.getDouble("valor")
                );
                
                bonusRecebidos.addBonus(bonus);
            }
            
            ps.close();
            conn.close();
        }

        return bonusRecebidos;
    }

    public BonusCollection getBonusFuncionarioByData(LocalDate data) throws SQLException {
        BonusCollection bonusRecebidos = new BonusCollection();

        var query = "SELECT * FROM funcionario_bonus WHERE data = ?";

        try (
            Connection conn = ConnectionSQLite.connect(); 
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setDate(1, Date.valueOf(data));
            
            ResultSet r = ps.executeQuery();
            
            while (r.next()) {
                Bonus bonus = new Bonus(
                        r.getInt("id"),
                        r.getString("nome"),
                        LocalDate.parse(r.getDate("data").toString()),
                        r.getDouble("valor")
                );
                
                bonusRecebidos.addBonus(bonus);
            }
            
            ps.close();
            conn.close();
        }

        return bonusRecebidos;
    }
}
