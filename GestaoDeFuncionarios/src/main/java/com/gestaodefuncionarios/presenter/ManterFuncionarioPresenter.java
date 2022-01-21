
package com.gestaodefuncionarios.presenter;

import com.gestaodefuncionarios.model.CargoEnum;
import com.gestaodefuncionarios.model.Funcionario;
import com.gestaodefuncionarios.view.ManterFuncionarioView;
import javax.swing.DefaultComboBoxModel;


public class ManterFuncionarioPresenter {

    private ManterFuncionarioView view;
    
    public ManterFuncionarioPresenter() {
        view = new ManterFuncionarioView();
        
        view.getjButtonFechar().addActionListener((e) -> {
            view.dispose();
        });
        
        view.getjButtonSalvar().addActionListener((e) -> {
            salvar();
        });
        
        initilizeComponents(false);
        
        view.setVisible(true);
        view.setLocationRelativeTo(view.getParent());
    }
    
    public ManterFuncionarioPresenter(Funcionario funcionario) {
        view = new ManterFuncionarioView();
        
        view.getjButtonEditar().addActionListener((e) -> {
            editar();
        });
        
        view.getjButtonExcluir().addActionListener((e) -> {
            excluir();
        });
        
        view.getjButtonFechar().addActionListener((e) -> {
            view.dispose();
        });
        
        view.getjButtonSalvar().addActionListener((e) -> {
            salvar();
        });
        
        initilizeComponents(true);
        
        view.setVisible(true);
        view.setLocationRelativeTo(view.getParent());
    }

    private void excluir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void editar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void salvar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void initilizeComponents(boolean modoVisualizacao) {
        view.getjButtonExcluir().setEnabled(modoVisualizacao);
        view.getjButtonEditar().setEnabled(modoVisualizacao);
        view.getjButtonSalvar().setEnabled(!modoVisualizacao);
        
        view.getjComboBoxCargo().setEditable(!modoVisualizacao);
        view.getjTextFieldAdmissao().setEditable(!modoVisualizacao);
        view.getjTextFieldFaltas().setEditable(!modoVisualizacao);
        view.getjTextFieldIdade().setEditable(!modoVisualizacao);
        view.getjTextFieldNome().setEditable(!modoVisualizacao);
        view.getjTextFieldSalario().setEditable(!modoVisualizacao);
        view.getjComboBoxBonus().setEditable(!modoVisualizacao);
        view.getjCheckBoxFuncionarioDoMes().setEnabled(!modoVisualizacao);
        
        // Cargos
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(CargoEnum.listDescriptions().toArray());
        view.getjComboBoxCargo().setModel(defaultComboBoxModel);
    }
    
}
