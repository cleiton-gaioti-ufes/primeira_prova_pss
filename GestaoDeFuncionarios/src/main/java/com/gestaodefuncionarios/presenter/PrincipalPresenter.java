package com.gestaodefuncionarios.presenter;

import com.gestaodefuncionarios.dao.BonusDAO;
import com.gestaodefuncionarios.dao.FuncionarioDAO;
import com.gestaodefuncionarios.factory.ConnectionSQLite;
import com.gestaodefuncionarios.view.PrincipalView;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PrincipalPresenter {

    private static PrincipalView view;

    public static void main(String[] args) throws ClassNotFoundException {
        view = new PrincipalView();
        init();
    }

    private static void init() throws ClassNotFoundException {
        
        ConnectionSQLite.checkDiretorioDb();

        var qtd = 0;

        try {

            FuncionarioDAO.createTableFuncionarios();

            BonusDAO.createTableBonus();

            qtd = FuncionarioDAO.countFuncionarios();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

            view.dispose();
            System.exit(1);
        }

        view.getjMenuItemBuscar().addActionListener((ActionEvent e) -> {
            new BuscarFuncionarioPresenter(view.getDesktop(), view.getjToggleButtonPersistencia());
        });

        view.getjMenuItemCalcular().addActionListener((ActionEvent e) -> {
            new CalcularSalarioPresenter(view.getDesktop(), view.getjToggleButtonPersistencia());
        });

        view.getjMenuItemNovo().addActionListener((e) -> {
            new ManterFuncionarioPresenter(view.getDesktop(), view.getjToggleButtonPersistencia());
        });

        view.getjToggleButtonPersistencia().addActionListener(e -> {

            if(view.getjToggleButtonPersistencia().isSelected()) {

                view.getjToggleButtonPersistencia().setText("Json");

            } else {

                view.getjToggleButtonPersistencia().setText("Txt");

            }
        });

        view.getjTextPaneQtdFuncionarios().setText("Funcionários cadastrados: " + qtd);
        view.getjTextPanePersistencia().setText("Persistência de log em");
        
        view.setSize(1400, 900);
        view.setVisible(true);
        view.setLocationRelativeTo(view.getParent());
    }
}
