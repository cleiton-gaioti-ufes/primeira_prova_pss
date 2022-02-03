package com.gestaodefuncionarios.view;

import javax.swing.JDesktopPane;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;

public class PrincipalView extends javax.swing.JFrame {

    public PrincipalView() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPanePersistencia = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPaneQtdFuncionarios = new javax.swing.JTextPane();
        jToggleButtonPersistencia = new javax.swing.JToggleButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenuFuncionario = new javax.swing.JMenu();
        jMenuItemBuscar = new javax.swing.JMenuItem();
        jMenuItemNovo = new javax.swing.JMenuItem();
        jMenuSalario = new javax.swing.JMenu();
        jMenuItemCalcular = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de gestão de pessoas");
        setResizable(false);

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 526, Short.MAX_VALUE)
        );

        jTextPane1.setEditable(false);
        jTextPane1.setText("Versão 1.0");
        jScrollPane2.setViewportView(jTextPane1);

        jTextPanePersistencia.setEditable(false);
        jTextPanePersistencia.setText("Persistência de log em ");
        jScrollPane4.setViewportView(jTextPanePersistencia);

        jTextPaneQtdFuncionarios.setEditable(false);
        jTextPaneQtdFuncionarios.setText("Funcionários cadastrados: ");
        jScrollPane3.setViewportView(jTextPaneQtdFuncionarios);

        jToggleButtonPersistencia.setText("Txt");

        jMenuFuncionario.setText("Funcionário");

        jMenuItemBuscar.setText("Buscar");
        jMenuFuncionario.add(jMenuItemBuscar);

        jMenuItemNovo.setText("Novo");
        jMenuFuncionario.add(jMenuItemNovo);

        jMenuBar2.add(jMenuFuncionario);

        jMenuSalario.setText("Salário");

        jMenuItemCalcular.setText("Calcular Salários");
        jMenuSalario.add(jMenuItemCalcular);

        jMenuBar2.add(jMenuSalario);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButtonPersistencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButtonPersistencia)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenu jMenuFuncionario;
    private javax.swing.JMenuItem jMenuItemBuscar;
    private javax.swing.JMenuItem jMenuItemCalcular;
    private javax.swing.JMenuItem jMenuItemNovo;
    private javax.swing.JMenu jMenuSalario;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPanePersistencia;
    private javax.swing.JTextPane jTextPaneQtdFuncionarios;
    private javax.swing.JToggleButton jToggleButtonPersistencia;
    // End of variables declaration//GEN-END:variables

    public JMenuItem getjMenuItemBuscar() {
        return jMenuItemBuscar;
    }

    public JMenuItem getjMenuItemCalcular() {
        return jMenuItemCalcular;
    }

    public JMenuItem getjMenuItemNovo() {
        return jMenuItemNovo;
    }

    public JDesktopPane getDesktop() {
        return desktop;
    }

    public JTextPane getjTextPanePersistencia() {
        return jTextPanePersistencia;
    }

    public JTextPane getjTextPaneQtdFuncionarios() {
        return jTextPaneQtdFuncionarios;
    }

    public JToggleButton getjToggleButtonPersistencia() {
        return jToggleButtonPersistencia;
    }
}
