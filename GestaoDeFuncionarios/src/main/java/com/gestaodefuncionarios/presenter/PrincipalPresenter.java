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
    private static int qtd;
    private static int log; // 0 - text; 1 - json

    public static void main(String[] args) throws ClassNotFoundException {
        view = new PrincipalView();
        init();
    }

    private static void init() throws ClassNotFoundException {
        
        ConnectionSQLite.checkDiretorioDb();

        qtd = 0;

        view.getjRadioButtonMenuItemLogJson().setSelected(false);

        try {

            FuncionarioDAO.createTableFuncionarios();

            BonusDAO.createTableBonus();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

            view.dispose();
            System.exit(1);
        }

        view.getjMenuItemBuscar().addActionListener((ActionEvent e) -> {
            new BuscarFuncionarioPresenter(view.getDesktop(), log);
        });

        view.getjMenuItemCalcular().addActionListener((ActionEvent e) -> {
            new CalcularSalarioPresenter(view.getDesktop(), log);
        });

        view.getjMenuItemNovo().addActionListener((e) -> {
            new ManterFuncionarioPresenter(view.getDesktop(), log);
        });

        view.getjRadioButtonMenuItemLogJson().addActionListener(e -> {
            if(view.getjRadioButtonMenuItemLogJson().isSelected()) {
                log = 1;
                view.getjTextPanePersistencia().setText("Persistência de log em json");
                view.getjRadioButtonMenuItemLogTxt().setSelected(false);
                
                var frames = view.getDesktop().getAllFrames();

                for(int i = 0; i < frames.length; i++) {
                    frames[i].dispose();
                }
            }
        });

        view.getjRadioButtonMenuItemLogTxt().addActionListener(e -> {
            if(view.getjRadioButtonMenuItemLogTxt().isSelected()){
                log = 0;
                view.getjTextPanePersistencia().setText("Persistência de log em txt");
                view.getjRadioButtonMenuItemLogJson().setSelected(false);

                var frames = view.getDesktop().getAllFrames();

                for(int i = 0; i < frames.length; i++) {
                    frames[i].dispose();
                }

            }
        });

        atualizarQuantidade();
        view.getjTextPanePersistencia().setText("Persistência de log em txt");

        view.setSize(1400, 900);
        view.setVisible(true);
        view.setLocationRelativeTo(view.getParent());
    }

    public static void atualizarQuantidade() {
        try {

            qtd = FuncionarioDAO.countFuncionarios();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        view.getjTextPaneQtdFuncionarios().setText("Funcionários cadastrados: " + qtd);
    }
}
