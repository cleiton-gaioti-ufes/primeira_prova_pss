
package com.gestaodefuncionarios.presenter;

import com.gestaodefuncionarios.view.BuscarFuncionarioView;

public class BuscarFuncionarioPresenter {

    public BuscarFuncionarioPresenter() {
        
        var view = new BuscarFuncionarioView();
        
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
            novo();
        });
        
        view.getjButtonVisualizar().addActionListener((e) -> {
            visualizar();
        });
        
        view.setVisible(true);
        view.setLocationRelativeTo(view.getParent());
    }

    private void buscar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void historico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void novo() {
        new ManterFuncionarioPresenter();
    }

    private void visualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
