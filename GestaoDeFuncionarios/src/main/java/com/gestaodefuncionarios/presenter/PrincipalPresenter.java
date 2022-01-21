
package com.gestaodefuncionarios.presenter;

import com.gestaodefuncionarios.view.PrincipalView;
import java.awt.event.ActionEvent;

public class PrincipalPresenter {
    
    public static void main(String[] args) {
        
        PrincipalView view = new PrincipalView();
        
        view.getjMenuItemBuscar().addActionListener((ActionEvent e) -> {
            new BuscarFuncionarioPresenter();
        });
        
        view.getjMenuItemCalcular().addActionListener((ActionEvent e) -> {
            new CalcularSalariosPresenter();
        });
        
        view.getjMenuItemNovo().addActionListener((e) -> {
            new ManterFuncionarioPresenter();
        });
        
        // TODO: add ferramentas actions
        
        view.setVisible(true);
        view.setLocationRelativeTo(view.getParent());   
    }
}
