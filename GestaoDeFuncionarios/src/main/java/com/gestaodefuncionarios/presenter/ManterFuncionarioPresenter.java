package com.gestaodefuncionarios.presenter;

import com.gestaodefuncionarios.dao.FuncionarioDAO;
import com.gestaodefuncionarios.model.enums.Cargo;
import com.gestaodefuncionarios.model.Funcionario;
import com.gestaodefuncionarios.model.enums.Graduacao;
import com.gestaodefuncionarios.view.ManterFuncionarioView;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

public class ManterFuncionarioPresenter {

    private ManterFuncionarioView view;
    private final FuncionarioDAO funcionarioDAO;

    public ManterFuncionarioPresenter(JDesktopPane desktop) {

        this.funcionarioDAO = new FuncionarioDAO();

        this.view = new ManterFuncionarioView();

        view.getjButtonFechar().addActionListener((e) -> {
            view.dispose();
        });

        view.getjButtonSalvar().addActionListener((e) -> {
            salvar();
        });

        inicializarComponents(false);

        desktop.add(view);
        view.setVisible(true);
    }

    public ManterFuncionarioPresenter(JDesktopPane desktop, Funcionario funcionario) {

        this.funcionarioDAO = new FuncionarioDAO();

        this.view = new ManterFuncionarioView();

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

        if (resposta == 1) {
            try {

                funcionarioDAO.remove(funcionario);

                JOptionPane.showMessageDialog(view, "Funcionário excluído com sucesso!");
                clear();

            } catch (SQLException e) {

                JOptionPane.showMessageDialog(view, "Não foi possivel excluir o funcionario " + funcionario.getNome(),
                        "Erro ao inserir funcionário", JOptionPane.ERROR_MESSAGE);

            }
        }

    }

    private Funcionario criarFuncionario() {
        try {

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

        } catch (RuntimeException exc) {

            JOptionPane.showMessageDialog(view, exc.getMessage());

        }

        return null;
    }

    private void salvar(Funcionario old) {
        try {
            var novo = criarFuncionario();

            funcionarioDAO.update(novo, old);

            JOptionPane.showMessageDialog(view, "Funcionário editado com sucesso ", "Editado com sucesso", JOptionPane.INFORMATION_MESSAGE);

            setModoVisualizacao(novo, false);
        } catch (SQLException ex) {

        }
    }

    private void salvar() {

        try {

            var novo = criarFuncionario();

            funcionarioDAO.insert(novo);

            JOptionPane.showMessageDialog(view, "Funcionário salvo com sucesso ", "Salvo com sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            clear();

        } catch (SQLException exc) {

            if (exc.getSQLState().equals("2067")) {

                JOptionPane.showMessageDialog(view, "Funcionário já cadastrado", "Erro ao inserir funcionário",
                        JOptionPane.ERROR_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(view,
                        "Erro ao inserir funcionário!\nCódigo do Erro: " + exc.getSQLState(),
                        "Erro ao inserir funcionário", JOptionPane.ERROR_MESSAGE);

            }

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

    private void clear() {
        this.view.getjComboBoxCargo().setSelectedIndex(0);
        this.view.getjComboBoxBonus().setSelectedIndex(0);
        this.view.getjComboBoxGraduacao().setSelectedIndex(0);
        this.view.getjTextFieldNome().setText("");
        this.view.getjTextFieldIdade().setText("");
        this.view.getjTextFieldSalario().setText("");
        this.view.getjTextFieldFaltas().setText("");
        this.view.getjTextFieldAdmissao().setText("");
        this.view.getjCheckBoxAuxilioTransporte().setSelected(false);
        ;
    }
}
