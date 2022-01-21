
package com.gestaodefuncionarios.presenter;

import com.gestaodefuncionarios.view.CalcularSalariosView;

public class CalcularSalariosPresenter {

    public CalcularSalariosPresenter() {
        var view = new CalcularSalariosView();
        
        view.getjButtonBuscar().addActionListener((e) -> {
            buscar();
        });
        
        view.getjButtonCalcular().addActionListener((e) -> {
            calcular();
        });
        
        view.getjButtonFechar().addActionListener((e) -> {
            view.dispose();
        });
        
        view.getjButtonListarTodos().addActionListener((e) -> {
            listar();
        });
        
        view.setVisible(true);
        view.setLocationRelativeTo(view.getParent());
    }

    private void buscar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void calcular() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
