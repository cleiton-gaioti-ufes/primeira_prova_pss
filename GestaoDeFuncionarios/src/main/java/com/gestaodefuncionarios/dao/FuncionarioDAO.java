package com.gestaodefuncionarios.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.gestaodefuncionarios.collection.FuncionarioCollection;
import com.gestaodefuncionarios.factory.ConnectionSQLite;
import com.gestaodefuncionarios.model.Funcionario;
import com.gestaodefuncionarios.model.HistoricoBonus;
import com.gestaodefuncionarios.model.enums.Cargo;
import com.gestaodefuncionarios.model.enums.Graduacao;

public class FuncionarioDAO {

    public static void createTableFuncionarios() throws SQLException {
        var query = "CREATE TABLE IF NOT EXISTS funcionario("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nome VARCHAR NOT NULL, "
                + "idade INTEGER NOT NULL, "
                + "cd_cargo INTEGER NOT NULL, "
                + "ds_cargo VARCHAR NOT NULL, "
                + "salario_base DOUBLE PRECISION NOT NULL, "
                + "faltas INTEGER NOT NULL, "
                + "dt_admissao DATE NOT NULL, "
                + "cd_graduacao INTEGER NOT NULL, "
                + "ds_graduacao VARCHAR NOT NULL, "
                + "fl_bonus_normal BOOLEAN NOT NULL, "
                + "fl_auxilio_transporte BOOLEAN NOT NULL, "
                + "UNIQUE (nome, idade, dt_admissao)"
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

    public void insert(Funcionario funcionario) throws SQLException {
        var query = "INSERT INTO funcionario(nome, idade, cd_cargo, ds_cargo, salario_base, faltas, "
                + "dt_admissao, cd_graduacao, ds_graduacao, fl_bonus_normal, fl_auxilio_transporte) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = ConnectionSQLite.connect(); 
            PreparedStatement ps = conn.prepareStatement(query)
        ) {
            
            ps.setString(1, funcionario.getNome());
            ps.setInt(2, funcionario.getIdade());
            ps.setInt(3, funcionario.getCargoId());
            ps.setString(4, funcionario.getCargoString());
            ps.setDouble(5, funcionario.getSalarioBase());
            ps.setInt(6, funcionario.getFaltas());
            ps.setDate(7, Date.valueOf(funcionario.getAdmissao()));
            ps.setInt(8, funcionario.getGraduacaoId());
            ps.setString(9, funcionario.getGraduacaoString());
            ps.setBoolean(10, funcionario.isBonusNormal());
            ps.setBoolean(11, funcionario.hasAuxilioTransporte());
            
            ps.execute();
            
            ps.close();
            conn.close();
        }
    }

    public void remove(Funcionario funcionario) throws SQLException {
        var query = "DELETE FROM funcionario "
                + "WHERE "
                + "nome = ? AND idade = ? AND dt_admissao = ?";

        try (
            Connection conn = ConnectionSQLite.connect(); 
            PreparedStatement ps = conn.prepareStatement(query)
        ) {
            
            ps.setString(1, funcionario.getNome());
            ps.setInt(2, funcionario.getIdade());
            ps.setDate(3, Date.valueOf(funcionario.getAdmissao()));
            
            ps.executeUpdate();
            
            ps.close();
            conn.close();
        }
    }

    public void update(Funcionario newFunc, Funcionario oldFunc) throws SQLException {
        var query = "UPDATE funcionario "
                + "SET nome = ?, "
                + "idade = ?, "
                + "cd_cargo = ?, "
                + "ds_cargo = ?, "
                + "salario_base = ?, "
                + "faltas = ?, "
                + "dt_admissao = ?, "
                + "cd_graduacao = ?, "
                + "ds_graduacao = ?, "
                + "fl_bonus_normal = ?, "
                + "fl_auxilio_transporte = ? "
                + "WHERE nome = ? AND idade = ? AND dt_admissao = ?";
        
        try (
            Connection conn = ConnectionSQLite.connect(); 
            PreparedStatement ps = conn.prepareStatement(query)
        ) {
            
            ps.setString(1, newFunc.getNome());
            ps.setInt(2, newFunc.getIdade());
            ps.setInt(3, newFunc.getCargoId());
            ps.setString(4, newFunc.getCargoString());
            ps.setDouble(5, newFunc.getSalarioBase());
            ps.setInt(6, newFunc.getFaltas());
            ps.setDate(7, Date.valueOf(newFunc.getAdmissao()));
            ps.setInt(8, newFunc.getGraduacaoId());
            ps.setString(9, newFunc.getGraduacaoString());
            ps.setBoolean(10, newFunc.isBonusNormal());
            ps.setBoolean(11, newFunc.hasAuxilioTransporte());
            ps.setString(12, oldFunc.getNome());
            ps.setInt(13, oldFunc.getIdade());
            ps.setDate(14, Date.valueOf(oldFunc.getAdmissao()));
            
            ps.executeUpdate();
            
            ps.close();
            conn.close();
        }
    }

    public FuncionarioCollection getFuncionarios() throws SQLException {
        FuncionarioCollection funcionarios = new FuncionarioCollection();

        var query = "SELECT * FROM funcionario ORDER BY nome";

        try (
            Connection conn = ConnectionSQLite.connect(); 
            Statement stmt = conn.createStatement()
        ) {
            
            ResultSet r = stmt.executeQuery(query);
            
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            while (r.next()) {
                Funcionario funcionario = new Funcionario(
                        r.getInt("id"),
                        r.getString("nome"),
                        r.getInt("idade"),
                        r.getDouble("salario_base"),
                        r.getInt("faltas"),
                        r.getDate("dt_admissao").toLocalDate().format(formatter).toString(),
                        Cargo.toEnum(r.getInt("cd_cargo")),
                        r.getBoolean("fl_bonus_normal"),
                        Graduacao.toEnum(r.getInt("cd_graduacao")),
                        r.getBoolean("fl_auxilio_transporte")
                );
                
                funcionarios.addFuncionario(funcionario);
            }
            
            stmt.close();
            conn.close();
        }

        return funcionarios;
    }

    public FuncionarioCollection getFuncionariosByName(String nome) throws SQLException {
        FuncionarioCollection funcionarios = new FuncionarioCollection();

        var query = "SELECT * FROM funcionario WHERE nome LIKE ? ORDER BY nome";

        try (
            Connection conn = ConnectionSQLite.connect(); 
            PreparedStatement ps = conn.prepareStatement(query)
        ) {
            
            ps.setString(1, "%" + nome + "%");
            
            ResultSet r = ps.executeQuery();
            
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            while (r.next()) {
                Funcionario funcionario = new Funcionario(
                        r.getInt("id"),
                        r.getString("nome"),
                        r.getInt("idade"),
                        r.getDouble("salario_base"),
                        r.getInt("faltas"),
                        r.getDate("dt_admissao").toLocalDate().format(formatter).toString(),
                        Cargo.toEnum(r.getInt("cd_cargo")),
                        r.getBoolean("fl_bonus_normal"),
                        Graduacao.toEnum(r.getInt("cd_graduacao")),
                        r.getBoolean("fl_auxilio_transporte")
                );
                
                funcionarios.addFuncionario(funcionario);
            }
            
            ps.close();
            conn.close();
        }

        return funcionarios;
    }

    public Funcionario getFuncionarioById(int id) throws SQLException {

        var query = "SELECT * FROM funcionario WHERE id = ?";

        try (
            Connection conn = ConnectionSQLite.connect(); 
            PreparedStatement ps = conn.prepareStatement(query)
        ) {
            
            ps.setInt(1, id);
            
            ResultSet r = ps.executeQuery();
            
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            Funcionario funcionario = new Funcionario(
                    r.getInt("id"),
                    r.getString("nome"),
                    r.getInt("idade"),
                    r.getDouble("salario_base"),
                    r.getInt("faltas"),
                    r.getDate("dt_admissao").toLocalDate().format(formatter).toString(),
                    Cargo.toEnum(r.getInt("cd_cargo")),
                    r.getBoolean("fl_bonus_normal"),
                    Graduacao.toEnum(r.getInt("cd_graduacao")),
                    r.getBoolean("fl_auxilio_transporte")
            );
            
            ps.close();
            conn.close();

            return funcionario;
        }
    }

    public List<HistoricoBonus> getHistoricoDeBonus(int idFuncionario) throws SQLException {
        var query = "SELECT fb.data, f.ds_cargo, fb.nome, fb.valor " 
                  + "FROM funcionario f INNER JOIN funcionario_bonus fb ON (f.id = fb.id_funcionario) "
                  + "WHERE f.id = ? "
                  + "ORDER BY fb.data DESC";

        List<HistoricoBonus> historico = new ArrayList<>();

        try (
            Connection conn = ConnectionSQLite.connect();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            
            ps.setInt(1, idFuncionario);

            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {

                var data = rs.getDate("data").toLocalDate();
                var cargo = rs.getString("ds_cargo");
                var nome = rs.getString("nome"); 
                var valor = rs.getDouble("valor");

                var bonus = new HistoricoBonus(data, cargo, nome, valor);

                historico.add(bonus);
            }

            conn.close();
            ps.close();
            
            return historico;
        }
    }

    public static int countFuncionarios() throws SQLException {
        var query = "SELECT COUNT(1) AS qtd FROM funcionario";

        try(
            Connection conn = ConnectionSQLite.connect();
            Statement st = conn.createStatement();
        ) {
            ResultSet rs = st.executeQuery(query);

            return rs.getInt("qtd");
        }
    }
}
