/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.gestaodefuncionarios.view;

import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author cleit
 */
public class CalcularSalarioView extends javax.swing.JInternalFrame {

    /**
     * Creates new form CalcularSalarioView
     */
    public CalcularSalarioView() {
        initComponents();
        formatarCampo();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCalcularSalarios = new javax.swing.JTable();
        jButtonFechar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButtonCalcular = new javax.swing.JButton();
        jFormattedTextFieldDataDoCalculo = new javax.swing.JFormattedTextField();

        setPreferredSize(new java.awt.Dimension(680, 392));

        jTableCalcularSalarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableCalcularSalarios.setToolTipText("");
        jScrollPane1.setViewportView(jTableCalcularSalarios);

        jButtonFechar.setText("Fechar");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Data do Cálculo");

        jButtonCalcular.setText("Calcular");

        jFormattedTextFieldDataDoCalculo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        jFormattedTextFieldDataDoCalculo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jFormattedTextFieldDataDoCalculo.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextFieldDataDoCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonFechar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonCalcular)))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jFormattedTextFieldDataDoCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonFechar)
                    .addComponent(jButtonCalcular))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formatarCampo() {    
        try {
            
            MaskFormatter mask = new MaskFormatter("##/##/####");
            mask.install(jFormattedTextFieldDataDoCalculo); 
        } catch (ParseException ex) {   
            JOptionPane.showMessageDialog(null, "Erro ao formatar campo de texto", "ERRO", JOptionPane.ERROR);   
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCalcular;
    private javax.swing.JButton jButtonFechar;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataDoCalculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCalcularSalarios;
    // End of variables declaration//GEN-END:variables

    public JButton getjButtonCalcular() {
        return jButtonCalcular;
    }

    public JButton getjButtonFechar() {
        return jButtonFechar;
    }

    public JTable getjTableCalcularSalarios() {
        return jTableCalcularSalarios;
    }

    public JFormattedTextField getjFormattedTextFieldDataDoCalculo() {
        return jFormattedTextFieldDataDoCalculo;
    }
    
}
