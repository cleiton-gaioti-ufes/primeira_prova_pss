package com.gestaodefuncionarios.presenter;

import com.gestaodefuncionarios.collection.FuncionarioCollection;
import com.gestaodefuncionarios.dao.BonusDAO;
import com.gestaodefuncionarios.dao.FuncionarioDAO;
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

    public CalcularSalarioPresenter(JDesktopPane desktop) {
        this.funcionarioDAO = new FuncionarioDAO();
        this.bonusDAO = new BonusDAO();
        
        try {
            
            this.funcionarios = funcionarioDAO.getFuncionarios();
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(view, "Falha ao consultar funcionario: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        this.tableModelFuncionarios = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Funcionário", "Data", "Salário Base (R$)", "Bônus (R$)", "Salário (R$)"}
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
            }
        });

        view.getjButtonFechar().addActionListener((e) -> {
            view.dispose();
        });

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
        
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.view.getjTableCalcularSalarios().setModel(tableModelFuncionarios);

        this.view.getjTableCalcularSalarios().getColumnModel().getColumn(1).setCellRenderer(centro);
        this.view.getjTableCalcularSalarios().getColumnModel().getColumn(2).setCellRenderer(centro);
        this.view.getjTableCalcularSalarios().getColumnModel().getColumn(3).setCellRenderer(centro);
        this.view.getjTableCalcularSalarios().getColumnModel().getColumn(4).setCellRenderer(centro);
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

                view.getjTableCalcularSalarios().setModel(tableModelFuncionarios);

            } catch (SQLException ex) {

                throw new RuntimeException("Falha ao calcular salário do funcionario " + f.getNome() + ":" + ex.getMessage());
            }
        });
        
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.view.getjTableCalcularSalarios().setModel(tableModelFuncionarios);
        this.view.getjTableCalcularSalarios().getColumnModel().getColumn(1).setCellRenderer(centro);
        this.view.getjTableCalcularSalarios().getColumnModel().getColumn(2).setCellRenderer(centro);
        this.view.getjTableCalcularSalarios().getColumnModel().getColumn(3).setCellRenderer(centro);
        this.view.getjTableCalcularSalarios().getColumnModel().getColumn(4).setCellRenderer(centro);
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
