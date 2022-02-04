package com.gestaodefuncionarios.presenter;

import com.gestaodefuncionarios.dao.FuncionarioDAO;
import com.gestaodefuncionarios.factory.PersistenciaLog;
import com.gestaodefuncionarios.model.enums.Cargo;
import com.gestaodefuncionarios.model.Funcionario;
import com.gestaodefuncionarios.model.enums.Graduacao;
import com.gestaodefuncionarios.view.ManterFuncionarioView;

import java.awt.Color;
import java.awt.Graphics;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

public class ManterFuncionarioPresenter {

    private ManterFuncionarioView view;
    private final FuncionarioDAO funcionarioDAO;
    private final int log;

    public ManterFuncionarioPresenter(JDesktopPane desktop, int log) {

        this.funcionarioDAO = new FuncionarioDAO();

        this.view = new ManterFuncionarioView();
        this.log = log;

        view.getjButtonFechar().addActionListener((e) -> {
            view.dispose();
        });

        view.getjButtonSalvar().addActionListener((e) -> {
            salvar();
        });

        inicializarComponents(false);

        view.setSize(690, 250);
        view.setLocation(0, 0);
        desktop.add(view);
        view.setVisible(true);
    }

    public ManterFuncionarioPresenter(JDesktopPane desktop, Funcionario funcionario, int log) {

        this.funcionarioDAO = new FuncionarioDAO();

        this.view = new ManterFuncionarioView();
        this.log = log;

        view.getjButtonFechar().addActionListener((e) -> {
            view.dispose();
        });

        view.getjButtonSalvar().addActionListener((e) -> {
            salvar(funcionario);
        });

        view.getjButtonEditar().addActionListener((e) -> {
            setModoVisualizacao(funcionario, true);
        });

        view.getjButtonExcluir().addActionListener((e) -> {
            excluir(funcionario);
        });

        inicializarComponents(true);

        setModoVisualizacao(funcionario, false);

        view.setSize(690, 250);
        view.setLocation(0, 402);
        desktop.add(view);
        view.setVisible(true);
    }

    private void excluir(Funcionario funcionario) {
        String[] options = { "Sim", "Não" };

        int resposta = JOptionPane.showOptionDialog(
                view,
                "Tem certeza que deseja excluir o funcionário " + funcionario.getNome() + "?",
                "Excluir funcionário",
                JOptionPane.YES_OPTION,
                JOptionPane.NO_OPTION,
                null,
                options,
                options[1]);

        if (resposta == 0) {
            try {

                funcionarioDAO.remove(funcionario);

                JOptionPane.showMessageDialog(view, "Funcionário excluído com sucesso!");

                PersistenciaLog.gravarRemocaoFuncionario(log, funcionario.getNome());

                PrincipalPresenter.atualizarQuantidade();
                view.dispose();

            } catch (SQLException e) {

                JOptionPane.showMessageDialog(view, "Não foi possivel excluir o funcionario " + funcionario.getNome(),
                        "Erro ao excluir funcionário", JOptionPane.ERROR_MESSAGE);

                PersistenciaLog.gravarFalha(log, "Erro ao excluir funcionário" + funcionario.getNome());


            }
        }

    }

    private Funcionario criarFuncionario() throws RuntimeException {
            var cargo = view.getjComboBoxCargo().getSelectedIndex();
            var nome = view.getjTextFieldNome().getText();
            var bonusNormal = view.getjComboBoxBonus().getSelectedIndex() == 1;
            var graduacao = view.getjComboBoxGraduacao().getSelectedIndex();
            var auxilioTransporte = view.getjCheckBoxAuxilioTransporte().isSelected();
            var admissao = view.getjTextFieldAdmissao().getText();
            var faltas = view.getjTextFieldFaltas().getText();
            var idade = view.getjTextFieldIdade().getText();
            var salario = view.getjTextFieldSalario().getText();

            var novo = new Funcionario(nome, idade, salario, faltas, admissao, Cargo.toEnum(cargo), bonusNormal,
                    Graduacao.toEnum(graduacao), auxilioTransporte);

            return novo;
    }

    private void salvar(Funcionario old) {
        try {

            var novo = criarFuncionario();
            
            funcionarioDAO.update(novo, old);

            JOptionPane.showMessageDialog(view, "Funcionário editado com sucesso ", "Editado com sucesso", JOptionPane.INFORMATION_MESSAGE);

            PrincipalPresenter.atualizarQuantidade();

            PersistenciaLog.gravarAlteracaoFuncionario(log, novo.getNome());

            view.dispose();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(view, "Erro ao editar funcionário!", "Erro ao editar funcionário", JOptionPane.ERROR_MESSAGE);

            PersistenciaLog.gravarFalha(log, "Erro ao editar funcionário");

        }
    }

    private void salvar() {

        try {

            var novo = criarFuncionario();

            funcionarioDAO.insert(novo);

            JOptionPane.showMessageDialog(view, "Funcionário salvo com sucesso ", "Salvo com sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            
            PrincipalPresenter.atualizarQuantidade();
            PersistenciaLog.gravarCriacaoFuncionario(log, novo.getNome());

            this.view.dispose();

        } catch (SQLException e) {

            if (e.getSQLState().equals("2067")) {

                JOptionPane.showMessageDialog(view, "Funcionário já cadastrado", "Erro ao inserir funcionário",
                        JOptionPane.ERROR_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(view,
                        "Erro ao inserir funcionário!\nCódigo do Erro: " + e.getSQLState(),
                        "Erro ao inserir funcionário", JOptionPane.ERROR_MESSAGE);

                PersistenciaLog.gravarFalha(log, "Erro ao inserir funcionário");
                
            }

        } catch(RuntimeException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }

    }

    private void inicializarComponents(boolean modoVisualizacao) {
        view.getjButtonExcluir().setEnabled(modoVisualizacao);
        view.getjButtonEditar().setEnabled(modoVisualizacao);
        view.getjButtonSalvar().setEnabled(!modoVisualizacao);

        // Cargos
        DefaultComboBoxModel<String> defaultComboBoxModelCargo = new DefaultComboBoxModel(Cargo.listDescriptions().toArray());
        view.getjComboBoxCargo().setModel(defaultComboBoxModelCargo);

        // Graduacões
        DefaultComboBoxModel<String> defaultComboBoxModelGraduacao = new DefaultComboBoxModel(Graduacao.listDescriptions().toArray());
        view.getjComboBoxGraduacao().setModel(defaultComboBoxModelGraduacao);
    }

    private void setModoVisualizacao(Funcionario funcionario, boolean editar) {
        this.view.getjComboBoxCargo().setEnabled(editar);
        this.view.getjComboBoxBonus().setEnabled(editar);
        this.view.getjComboBoxGraduacao().setEnabled(editar);
        this.view.getjTextFieldAdmissao().setEditable(editar);
        this.view.getjTextFieldFaltas().setEditable(editar);
        this.view.getjTextFieldIdade().setEditable(editar);
        this.view.getjTextFieldNome().setEditable(editar);
        this.view.getjTextFieldSalario().setEditable(editar);
        
        view.getjButtonSalvar().setEnabled(editar);
        view.getjButtonExcluir().setEnabled(!editar);
        view.getjButtonEditar().setEnabled(!editar);
        
        if(!editar) {
            this.view.getjComboBoxBonus().setRenderer(new DefaultListCellRenderer() {
                @Override
                public void paint(Graphics g) {
                    setForeground(Color.black);
                    super.paint(g);
                }
            });

            this.view.getjComboBoxCargo().setRenderer(new DefaultListCellRenderer() {
                @Override
                public void paint(Graphics g) {
                    setForeground(Color.black);
                    super.paint(g);
                }
            });

            this.view.getjComboBoxGraduacao().setRenderer(new DefaultListCellRenderer() {
                @Override
                public void paint(Graphics g) {
                    setForeground(Color.black);
                    super.paint(g);
                }
            });
        }
        
        if (!editar) {
            this.view.getjComboBoxCargo().setSelectedIndex(funcionario.getCargoId());
            this.view.getjTextFieldFaltas().setText(String.valueOf(funcionario.getFaltas()));
            this.view.getjTextFieldIdade().setText(String.valueOf(funcionario.getIdade()));
            this.view.getjTextFieldNome().setText(funcionario.getNome());
            this.view.getjComboBoxBonus().setSelectedIndex(funcionario.isBonusNormal() ? 1 : 0);
            this.view.getjComboBoxGraduacao().setSelectedIndex(funcionario.getGraduacaoId());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.view.getjTextFieldAdmissao().setText(funcionario.getAdmissao().format(formatter).toString());

            DecimalFormat df = new DecimalFormat("#,###.00");
            this.view.getjTextFieldSalario().setText(String.valueOf(df.format(funcionario.getSalarioBase())));
        }
    }
}
