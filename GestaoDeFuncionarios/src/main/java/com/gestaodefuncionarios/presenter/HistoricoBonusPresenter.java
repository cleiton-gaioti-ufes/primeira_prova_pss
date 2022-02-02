
package com.gestaodefuncionarios.presenter;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import com.gestaodefuncionarios.dao.FuncionarioDAO;
import com.gestaodefuncionarios.factory.PersistenciaLog;
import com.gestaodefuncionarios.model.HistoricoBonus;
import com.gestaodefuncionarios.view.HistoricoBonusView;

public class HistoricoBonusPresenter {
    
    private final HistoricoBonusView view;
    private final DefaultTableModel tableModelHistorico;
    private final FuncionarioDAO funcionarioDAO;

    public HistoricoBonusPresenter(JDesktopPane desktop, int idFuncionario, JToggleButton btnLog) {
        this.view = new HistoricoBonusView();

        funcionarioDAO = new FuncionarioDAO();

        this.tableModelHistorico = new DefaultTableModel(
            new Object[][] {}, 
            new String[] {"Data do Cálculo", "Cargo", "Tipo de Bônus", "Valor do Bônus"}
        ) {

            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };

        this.tableModelHistorico.setNumRows(0);

        try {

            List<HistoricoBonus> historico = funcionarioDAO.getHistoricoDeBonus(idFuncionario);
            
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            DecimalFormat df = new DecimalFormat("#,###.00");

            for(HistoricoBonus bonus: historico) {
                tableModelHistorico.addRow(
                    new Object[] {
                        bonus.getData().format(formatter), 
                        bonus.getCargo(), 
                        bonus.getTipoBonus(), 
                        df.format(bonus.getValor())
                    }
                );
            }

            this.view.getjTableHistorico().setModel(this.tableModelHistorico);

        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(view, "Falha ao consultar funcionario: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

            PersistenciaLog.gravarFalha(btnLog.isSelected(), "Falha ao consultar funcionario");
        }

        this.view.setVisible(true);
        desktop.add(view);
    }
}
