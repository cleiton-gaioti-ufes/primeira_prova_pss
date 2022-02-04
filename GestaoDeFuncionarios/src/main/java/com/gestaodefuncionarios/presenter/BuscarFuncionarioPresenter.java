package com.gestaodefuncionarios.presenter;

import com.gestaodefuncionarios.collection.FuncionarioCollection;
import com.gestaodefuncionarios.dao.FuncionarioDAO;
import com.gestaodefuncionarios.factory.PersistenciaLog;
import com.gestaodefuncionarios.view.BuscarFuncionarioView;

import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class BuscarFuncionarioPresenter {

    private BuscarFuncionarioView view;
    private FuncionarioCollection funcionarios;
    private final DefaultTableModel tableModelFuncionarios;
    private final FuncionarioDAO funcionarioDAO;
    private final JDesktopPane desktop;
    private final int log;

    public BuscarFuncionarioPresenter(JDesktopPane desktop, int log) {
        this.funcionarioDAO = new FuncionarioDAO();

        this.view = new BuscarFuncionarioView();

        this.desktop = desktop;

        this.log = log;

        try {

            this.funcionarios = funcionarioDAO.getFuncionarios();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(view, "Erro ao consultar funcionários");

            PersistenciaLog.gravarFalha(log, "Erro ao consultar funcionários");
        }

        this.tableModelFuncionarios = new DefaultTableModel(
                new Object[][] {},
                new String[] { "ID", "Nome", "Idade", "Função", "Salário Base (R$)" }) {

            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };

        carregarTabela();

        view.getjButtonBuscar().addActionListener((e) -> {
            buscar();
        });

        view.getjButtonFechar().addActionListener((e) -> {
            view.dispose();
        });

        view.getjButtonHistorico().addActionListener((e) -> {
            historico();
        });

        view.getjButtonNovo().addActionListener((e) -> {
            new ManterFuncionarioPresenter(view.getDesktopPane(), log);
        });

        view.getjButtonVisualizar().addActionListener((e) -> {
            visualizar();
        });

        view.getjButtonRefresh().addActionListener(e -> {
            carregarTabela();
        });

        view.setSize(690, 550);
        view.setLocation(0, 255);
        view.setVisible(true);
        desktop.add(view);
    }

    private void carregarTabela() {
        this.tableModelFuncionarios.setNumRows(0);

        this.view.getjTableFuncionarios().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DecimalFormat df = new DecimalFormat("#,###.00");

        try {
            this.funcionarios = funcionarioDAO.getFuncionarios();

            this.funcionarios.getFuncionarios().forEach(f -> {
                tableModelFuncionarios.addRow(
                        new Object[] {
                                f.getId(),
                                f.getNome(),
                                f.getIdade(),
                                f.getCargoString(),
                                df.format(f.getSalarioBase())
                        });
            });

            DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
            centro.setHorizontalAlignment(SwingConstants.CENTER);

            DefaultTableCellRenderer right = new DefaultTableCellRenderer();
            right.setHorizontalAlignment(SwingConstants.RIGHT);

            DefaultTableCellRenderer left = new DefaultTableCellRenderer();
            left.setHorizontalAlignment(SwingConstants.LEFT);

            this.view.getjTableFuncionarios().setModel(tableModelFuncionarios);
            this.view.getjTableFuncionarios().getColumnModel().getColumn(0).setCellRenderer(right);
            this.view.getjTableFuncionarios().getColumnModel().getColumn(1).setCellRenderer(left);
            this.view.getjTableFuncionarios().getColumnModel().getColumn(2).setCellRenderer(centro);
            this.view.getjTableFuncionarios().getColumnModel().getColumn(3).setCellRenderer(left);
            this.view.getjTableFuncionarios().getColumnModel().getColumn(4).setCellRenderer(right);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Falha ao consultar funcionarios: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);

            PersistenciaLog.gravarFalha(log, "Erro ao consultar funcionários");
        }
    }

    private void buscar() {
        var nome = this.view.getjTextFieldNome().getText();

        try {

            if (nome.isBlank() || nome.isEmpty()) {

                this.funcionarios = funcionarioDAO.getFuncionarios();

            } else {

                this.funcionarios = funcionarioDAO.getFuncionariosByName(nome);

            }

            carregarTabela();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(view, "Falha ao consultar funcionario: " + ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);

            PersistenciaLog.gravarFalha(log, "Erro ao consultar funcionários");
        }
    }

    private void historico() {
        var row = this.view.getjTableFuncionarios().getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(view, "Selecione uma linha!");

        } else {

            var id = Integer.parseInt(this.view.getjTableFuncionarios().getValueAt(row, 0).toString());

            new HistoricoBonusPresenter(desktop, id, log);

            PersistenciaLog.gravarConsultaBonusFuncionario(log,
                    this.view.getjTableFuncionarios().getValueAt(row, 1).toString());
        }
    }

    private void visualizar() {
        var row = view.getjTableFuncionarios().getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(view, "Selecione uma linha!");

        } else {

            var id = Integer.parseInt(this.view.getjTableFuncionarios().getValueAt(row, 0).toString());

            try {

                var funcionario = funcionarioDAO.getFuncionarioById(id);

                new ManterFuncionarioPresenter(this.desktop, funcionario, log);

                carregarTabela();
            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(view, "Falha ao consultar funcionario: " + ex.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);

                PersistenciaLog.gravarFalha(log, "Falha ao consultar funcionario");

            }

        }
    }

}
