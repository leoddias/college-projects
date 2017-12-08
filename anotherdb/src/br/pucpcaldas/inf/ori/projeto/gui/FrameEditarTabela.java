/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas.inf.ori.projeto.gui;

import au.com.bytecode.opencsv.CSVReader;
import br.pucpcaldas.inf.ori.projeto.core.Session;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Shu
 */
public class FrameEditarTabela extends javax.swing.JFrame {

    public FrameEditarTabela() {

    }

    public FrameEditarTabela(String pathAtt, String att) throws FileNotFoundException, IOException {
        initComponents();
        System.out.println("Caminho att: " + pathAtt);
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, new String[]{"Nome", "Tipo"});
        CSVReader readerColuna = new CSVReader(new FileReader(pathAtt));
        List<String[]> lista = readerColuna.readAll();
        readerColuna.close();

        for (int i = 0; i < lista.size(); i++) {
            modelo.addRow(lista.get(i));
        }
        modelo.addRow(new String[]{null,null});
        modelo.addRow(new String[]{null,null});
        modelo.addRow(new String[]{null,null});
        modelo.addRow(new String[]{null,null});
        tableCampos.setModel(modelo);
        jtfNomeAtual.setText(att);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtfNomeTabela = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCampos = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jtfNomeAtual = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Tabela");

        jLabel1.setText("Renomear para:");

        tableCampos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nome", "Tipo"
            }
        ));
        jScrollPane1.setViewportView(tableCampos);

        jButton2.setText("Confirmar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Nome atual da Tabela:");

        jtfNomeAtual.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtfNomeTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNomeAtual)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfNomeAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfNomeTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String nomeTabela = jtfNomeTabela.getText();
        String identificador = "";
        String tipo = "";
        if (jtfNomeTabela.getText().isEmpty()) {

            try {
                BufferedWriter buffWrite = new BufferedWriter(new FileWriter(Session.dirTabelas + "\\" + jtfNomeAtual.getText() + ".att"));
                //FileOutputStream arquivoAtt = new FileOutputStream();
                for (int i = 0; i < contaLinhas(tableCampos); i++) {
                    identificador = tableCampos.getValueAt(i, 0).toString();
                    tipo = tableCampos.getValueAt(i, 1).toString();
                    buffWrite.append(identificador+","+tipo);
                    buffWrite.append('\n');
                }
                buffWrite.close();
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrameEditarTabela.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FrameEditarTabela.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            //RENAME
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private int contaLinhas(JTable tabela) {
        int contador = 0;
        for (int i = 0; (i < tabela.getRowCount() && tabela.getValueAt(i, 0) != null); i++) {
            if (!tabela.getValueAt(i, 0).toString().isEmpty()) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameEditarTabela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtfNomeAtual;
    private javax.swing.JTextField jtfNomeTabela;
    private javax.swing.JTable tableCampos;
    // End of variables declaration//GEN-END:variables
}