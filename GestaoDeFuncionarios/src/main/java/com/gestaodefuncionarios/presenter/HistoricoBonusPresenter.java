
package com.gestaodefuncionarios.presenter;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
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

        this.view.getjButtonFechar().addActionListener(e -> {
            this.view.dispose();
        });

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
            var funcionario = funcionarioDAO.getFuncionarioById(idFuncionario);

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

            this.view.getjLabelNomeFuncionario().setText(funcionario.getNome() + " - " + funcionario.getCargoString());

            DefaultTableCellRenderer center = new DefaultTableCellRenderer();
            center.setHorizontalAlignment(SwingConstants.CENTER);
    
            DefaultTableCellRenderer right = new DefaultTableCellRenderer();
            right.setHorizontalAlignment(SwingConstants.RIGHT);
    
            DefaultTableCellRenderer left = new DefaultTableCellRenderer();
            left.setHorizontalAlignment(SwingConstants.LEFT);
            
            this.view.getjTableHistorico().setModel(this.tableModelHistorico);
            this.view.getjTableHistorico().getColumnModel().getColumn(0).setCellRenderer(center);
            this.view.getjTableHistorico().getColumnModel().getColumn(1).setCellRenderer(left);
            this.view.getjTableHistorico().getColumnModel().getColumn(2).setCellRenderer(left);
            this.view.getjTableHistorico().getColumnModel().getColumn(3).setCellRenderer(right);

        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(view, "Falha ao consultar funcionario: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

            PersistenciaLog.gravarFalha(btnLog.isSelected(), "Falha ao consultar funcionario");
        }

        view.setSize(690, 400);
        view.setLocation(695, 405);
        this.view.setVisible(true);
        desktop.add(view);
    }
}
