package com.gestaodefuncionarios.view;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class PrincipalView extends javax.swing.JFrame {

    public PrincipalView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenuFuncionario = new javax.swing.JMenu();
        jMenuItemBuscar = new javax.swing.JMenuItem();
        jMenuItemNovo = new javax.swing.JMenuItem();
        jMenuSalario = new javax.swing.JMenu();
        jMenuItemCalcular = new javax.swing.JMenuItem();
        jMenuFerramentas = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de gestão de pessoas");
        setPreferredSize(new java.awt.Dimension(600, 400));

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

        jMenuFerramentas.setText("Ferramentas");
        jMenuBar2.add(jMenuFerramentas);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 384, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenu jMenuFerramentas;
    private javax.swing.JMenu jMenuFuncionario;
    private javax.swing.JMenuItem jMenuItemBuscar;
    private javax.swing.JMenuItem jMenuItemCalcular;
    private javax.swing.JMenuItem jMenuItemNovo;
    private javax.swing.JMenu jMenuSalario;
    // End of variables declaration//GEN-END:variables

    public JMenu getjMenuFerramentas() {
        return jMenuFerramentas;
    }

    public JMenuItem getjMenuItemBuscar() {
        return jMenuItemBuscar;
    }

    public JMenuItem getjMenuItemCalcular() {
        return jMenuItemCalcular;
    }

    public JMenuItem getjMenuItemNovo() {
        return jMenuItemNovo;
    }
}
