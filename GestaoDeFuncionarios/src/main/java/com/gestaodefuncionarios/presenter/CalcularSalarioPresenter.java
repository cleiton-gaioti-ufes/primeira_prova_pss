package com.gestaodefuncionarios.presenter;

import com.gestaodefuncionarios.collection.FuncionarioCollection;
import com.gestaodefuncionarios.dao.BonusDAO;
import com.gestaodefuncionarios.dao.FuncionarioDAO;
import com.gestaodefuncionarios.factory.PersistenciaLog;
import com.gestaodefuncionarios.view.CalcularSalarioView;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CalcularSalarioPresenter {

    private CalcularSalarioView view;
    private FuncionarioCollection funcionarios;
    private final DefaultTableModel tableModelFuncionarios;
    private final FuncionarioDAO funcionarioDAO;
    private final BonusDAO bonusDAO;
    private final int log;

    public CalcularSalarioPresenter(JDesktopPane desktop, int log) {
        this.funcionarioDAO = new FuncionarioDAO();
        this.bonusDAO = new BonusDAO();
        this.log = log;
        
        try {
            
            this.funcionarios = funcionarioDAO.getFuncionarios();
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(view, "Falha ao consultar funcionario: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

            PersistenciaLog.gravarFalha(log, "Falha ao consultar funcionario");
            
        }
        
        this.tableModelFuncionarios = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Funcionário", "Admissão", "Salário Base (R$)", "Bônus (R$)", "Salário (R$)"}
        ) {
            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };

        this.view = new CalcularSalarioView();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        this.view.getjFormattedTextFieldDataDoCalculo().setText(LocalDate.now().format(formatter));

        carregarTabela();

        view.getjButtonCalcular().addActionListener((e) -> {
            try {
                calcular();
            } catch (HeadlessException | SQLException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

                PersistenciaLog.gravarFalha(log, ex.getMessage());
            }
        });

        view.getjButtonFechar().addActionListener((e) -> {
            view.dispose();
        });

        view.setSize(690, 400);
        view.setLocation(695, 0);
        desktop.add(view);
        view.setVisible(true);
    }

    private void carregarTabela() {
        this.tableModelFuncionarios.setNumRows(0);

        this.view.getjTableCalcularSalarios().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        DecimalFormat df = new DecimalFormat("#,###.00");

        this.funcionarios.getFuncionarios().forEach(f -> {
            tableModelFuncionarios.addRow(
                    new Object[]{
                        f.getNome(),
                        f.getAdmissao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        df.format(f.getSalarioBase()),
                        "-",
                        "-"
                    }
            );
        });
        
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer right = new DefaultTableCellRenderer();
        right.setHorizontalAlignment(SwingConstants.RIGHT);
        
        this.view.getjTableCalcularSalarios().setModel(tableModelFuncionarios);

        this.view.getjTableCalcularSalarios().getColumnModel().getColumn(1).setCellRenderer(center);
        this.view.getjTableCalcularSalarios().getColumnModel().getColumn(2).setCellRenderer(right);
        this.view.getjTableCalcularSalarios().getColumnModel().getColumn(3).setCellRenderer(center);
        this.view.getjTableCalcularSalarios().getColumnModel().getColumn(4).setCellRenderer(center);
    }
        
        
    private void carregarTabela(LocalDate data) {
        this.tableModelFuncionarios.setNumRows(0);

        this.view.getjTableCalcularSalarios().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        DecimalFormat df = new DecimalFormat("#,###.00");

        this.funcionarios.getFuncionarios().forEach(f -> {

            try {
                var salarioFinal = f.calcularSalario(data);

                tableModelFuncionarios.addRow(
                        new Object[] {
                            f.getNome(),
                            f.getAdmissao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            df.format(f.getSalarioBase()),
                            df.format(salarioFinal - f.getSalarioBase()),
                            df.format(salarioFinal)
                        }
                );

            } catch (SQLException ex) {

                throw new RuntimeException("Falha ao calcular salário do funcionario " + f.getNome() + ":" + ex.getMessage());
            }
        });

        PersistenciaLog.gravarCalculoDeSalarioFuncionario(log, this.funcionarios);

        view.getjTableCalcularSalarios().setModel(tableModelFuncionarios);
        
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer right = new DefaultTableCellRenderer();
        right.setHorizontalAlignment(SwingConstants.RIGHT);

        
        this.view.getjTableCalcularSalarios().setModel(tableModelFuncionarios);
        this.view.getjTableCalcularSalarios().getColumnModel().getColumn(1).setCellRenderer(center);
        this.view.getjTableCalcularSalarios().getColumnModel().getColumn(2).setCellRenderer(right);
        this.view.getjTableCalcularSalarios().getColumnModel().getColumn(3).setCellRenderer(right);
        this.view.getjTableCalcularSalarios().getColumnModel().getColumn(4).setCellRenderer(right);
    }

    private void calcular() throws HeadlessException, SQLException {
        try {
            
            var data = LocalDate.parse(this.view.getjFormattedTextFieldDataDoCalculo().getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")); 
        
            if(salarioJaCalculado(data)) {
            
                String[] options = {"Sim", "Não"};

                int resposta = JOptionPane.showOptionDialog(
                        view,
                        "Salário já calculado para esse dia!\nDeseja recalcular?",
                        "Salário já calculado!",
                        JOptionPane.YES_OPTION,
                        JOptionPane.NO_OPTION,
                        null,
                        options, 
                        options[1]
                );

                if(resposta == 0) {
                    bonusDAO.deleteBonusByData(data);
                    carregarTabela(data);
                }
                
            } else {
                carregarTabela(data);
            }
            
        } catch(DateTimeParseException ex) {
            
            JOptionPane.showMessageDialog(view, "Data inválida!");
        }
    }
    
    private boolean salarioJaCalculado(LocalDate data) throws SQLException {
        var bonusRecebios = bonusDAO.getBonusFuncionarioByData(data);

        return (bonusRecebios.getBonusRecebidos().size() > 0);
    }

}
