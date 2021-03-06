/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas.inf.ori.projeto.gui;

import au.com.bytecode.opencsv.CSVReader;
import br.pucpcaldas.inf.ori.projeto.core.BaseReport;
import br.pucpcaldas.inf.ori.projeto.core.SQL;
import br.pucpcaldas.inf.ori.projeto.core.ListaTabelas;
import br.pucpcaldas.inf.ori.projeto.core.Session;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author OCTAVIO-NB
 */
public class FramePrincipal extends javax.swing.JFrame {

    String[] colunasNomes;
    String[] colunasTipos;

    SQL q = new SQL();

    public FramePrincipal() {
        System.out.println("Permissoes: " + Session.canCreate + Session.canDelete + Session.canInsert + Session.canSelect + Session.canUpdate);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
        initComponents();
        preencheListTabelas();
        btnUser.setText("LOGADO COMO: [ " + Session.userName + " ]");
        btnBanco.setText("BANCO ATUAL: [ " + Session.banco + " ]");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfQuery = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableResultSet = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listResultSet = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        listTabelas = new java.awt.List();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        btnUser = new javax.swing.JButton();
        btnBanco = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tfQuery.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfQueryActionPerformed(evt);
            }
        });
        tfQuery.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfQueryKeyReleased(evt);
            }
        });

        jButton1.setText("Executar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Limpar Query");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tableResultSet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableResultSet);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/pucpcaldas/inf/ori/projeto/images/bd_exportar.png"))); // NOI18N
        jButton4.setText("Exportar Resultado Como PDF...");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setText("ResultGrid:");

        jLabel3.setText("ResultSet:");

        listResultSet.setColumns(20);
        listResultSet.setRows(5);
        jScrollPane3.setViewportView(listResultSet);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane3)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jButton4)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        jLabel1.setText("Tabelas:");

        listTabelas.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        listTabelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listTabelasMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listTabelasMousePressed(evt);
            }
        });
        listTabelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listTabelasActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/pucpcaldas/inf/ori/projeto/images/tabela_add.png"))); // NOI18N
        jButton5.setText("           Nova Tabela");
        jButton5.setEnabled(Session.canCreate);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setText("Editar tabela");
        jButton7.setEnabled(Session.canUpdate);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        btnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserActionPerformed(evt);
            }
        });

        btnBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBancoActionPerformed(evt);
            }
        });

        jButton6.setText("Inserir dados");
        jButton6.setEnabled(Session.canInsert);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setText("Remover dados");
        jButton8.setEnabled(Session.canDelete);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton3.setText("Modificar dados");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton9.setText("Atualizar Lista");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Mudar banco");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Query SQL:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(listTabelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                        .addComponent(btnUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2))
                        .addComponent(tfQuery, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnUser, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton10)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jButton9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(listTabelas, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(19, 19, 19))))
        );

        tfQuery.getAccessibleContext().setAccessibleName("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tfQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfQueryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfQueryActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            listResultSet.append(q.ExecutaSQL(tfQuery.getText()) + "\n");
            preencheListTabelas();
        } catch (InterruptedException ex) {
            Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            CSVReader readerColuna = new CSVReader(new FileReader(Session.dirTabelas + "\\" + Session.tabelaAtual + ".att"));
            List<String[]> aux = readerColuna.readAll();
            readerColuna.close();
            colunasNomes = new String[aux.size()];
            colunasTipos = new String[aux.size()];
            for (int i = 0; i < aux.size(); i++) {
                colunasNomes[i] = aux.get(i)[0].toString();
                colunasTipos[i] = aux.get(i)[1].toString();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, colunasNomes);
        tableResultSet.setModel(modelo);

        try {
            CSVReader reader = new CSVReader(new FileReader(Session.dirExecutaveis + "ResultSet.txt"));
            List<String[]> resultSet = reader.readAll();
            reader.close();

            for (String[] registro : resultSet) {
                modelo.addRow(registro);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        preencheListTabelas();


    }//GEN-LAST:event_jButton1ActionPerformed

    private void listTabelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listTabelasActionPerformed
        String tabelaAtual = listTabelas.getSelectedItem();
        Session.tabelaAtual = listTabelas.getSelectedItem();
        tfQuery.setText("SELECT * FROM " + tabelaAtual);
    }//GEN-LAST:event_listTabelasActionPerformed

    private void listTabelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listTabelasMouseClicked

    }//GEN-LAST:event_listTabelasMouseClicked

    private void listTabelasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listTabelasMousePressed

    }//GEN-LAST:event_listTabelasMousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        tfQuery.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //this.setVisible(false);
        new FrameNovaTabela().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (listTabelas.getSelectedItem() != null) {
            try {
                //Session.dirTabelas+"\\"+Session.tabelaAtual+".att")
                new FrameEditarTabela(Session.dirTabelas + "\\" + listTabelas.getSelectedItem() + ".att", listTabelas.getSelectedItem()).setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione primeiro uma tabela!");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tfQueryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfQueryKeyReleased
        tfQuery.setText(tfQuery.getText().toUpperCase());
    }//GEN-LAST:event_tfQueryKeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String log = "";
        if (listTabelas.getSelectedItem() != null) {
            try {
                CSVReader readerColuna = new CSVReader(new FileReader(Session.dirTabelas + "\\" + listTabelas.getSelectedItem() + ".att"));
                List<String[]> aux = readerColuna.readAll();
                readerColuna.close();
                colunasNomes = new String[aux.size()];
                colunasTipos = new String[aux.size()];
                String query = "INSERT INTO " + listTabelas.getSelectedItem() + " VALUES ";
                for (int i = 1; i < aux.size(); i++) {
                    colunasNomes[i] = aux.get(i)[0].toString();
                    colunasTipos[i] = aux.get(i)[1].toString();
                    query = query + (JOptionPane.showInputDialog("Digite o valor para o campo " + aux.get(i)[0].toString() + " (" + aux.get(i)[1].toString() + ")") + " ");
                }
                log = q.ExecutaSQL(query);
                JOptionPane.showMessageDialog(rootPane, log);
                listResultSet.append(log + "\n");

            } catch (FileNotFoundException ex) {
                Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione primeiro uma tabela!");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String log = "";
        if (listTabelas.getSelectedItem() != null) {
            String query = "DELETE FROM " + listTabelas.getSelectedItem() + " WHERE " + JOptionPane.showInputDialog("Digite a chave primaria:");
            try {
                log = q.ExecutaSQL(query);
                JOptionPane.showMessageDialog(rootPane, log);
                listResultSet.append(log + "\n");
            } catch (InterruptedException ex) {
                Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione primeiro uma tabela!");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserActionPerformed
        FrameInfoUsuario frame = new FrameInfoUsuario();
        frame.show();
    }//GEN-LAST:event_btnUserActionPerformed

    private void btnBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBancoActionPerformed

        FrameInfoBanco frame = new FrameInfoBanco();
        frame.show();
    }//GEN-LAST:event_btnBancoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showSaveDialog(null);
        if (i != 1) {
            File arquivo = file.getSelectedFile();

            System.out.println(arquivo.getAbsolutePath());
            String[] header = new String[tableResultSet.getColumnCount()];
            for (i = 0; i < tableResultSet.getColumnCount(); i++) {
                header[i] = tableResultSet.getColumnName(i);
            }

            BaseReport report = new BaseReport(JOptionPane.showInputDialog("Digite o título do relatório:"), header);

            try {
                report.runReport(tableResultSet, arquivo.getAbsolutePath());
                int abrir = JOptionPane.showConfirmDialog(rootPane, "Deseja abrir o documento agora?", null, JOptionPane.YES_NO_OPTION);
                if (abrir == JOptionPane.YES_OPTION) {
                    java.awt.Desktop.getDesktop().open(new File(arquivo.getAbsolutePath() + ".pdf"));
                }
            } catch (Exception ex) {
                Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (listTabelas.getSelectedItem() != null) {
            String query = "DELETE FROM " + listTabelas.getSelectedItem() + " WHERE " + JOptionPane.showInputDialog("Digite a chave primaria:");
            try {
                q.ExecutaSQL(query);
            } catch (InterruptedException ex) {
                Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                CSVReader readerColuna = new CSVReader(new FileReader(Session.dirTabelas + "\\" + listTabelas.getSelectedItem() + ".att"));
                List<String[]> aux = readerColuna.readAll();
                readerColuna.close();
                colunasNomes = new String[aux.size()];
                colunasTipos = new String[aux.size()];
                query = "INSERT INTO " + listTabelas.getSelectedItem() + " VALUES ";
                for (int i = 1; i < aux.size(); i++) {
                    colunasNomes[i] = aux.get(i)[0].toString();
                    colunasTipos[i] = aux.get(i)[1].toString();
                    query = query + (JOptionPane.showInputDialog("Digite o valor para o campo " + aux.get(i)[0].toString() + " (" + aux.get(i)[1].toString() + ")") + " ");
                }
                q.ExecutaSQL(query);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione primeiro uma tabela!");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        preencheListTabelas();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        this.dispose();
        Session.banco = "";
        Session.dirTabelas = "Tabelas\\";
        new FrameConsultaBanco().show();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            Runtime.getRuntime().exec("CRYPTER.exe");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "ERROR", "ERRO INESPERADO CRYPTER.EXE", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_formWindowClosing

    public void preencheListTabelas() {
        listTabelas.removeAll();
        ListaTabelas lista = new ListaTabelas(Session.dirTabelas);
        ArrayList<String> listaTabelas = new ArrayList();
        listaTabelas = lista.getLista();

        for (int i = 0; i < listaTabelas.size(); i++) {
            listTabelas.add(listaTabelas.get(i));
        }

    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanco;
    private javax.swing.JButton btnUser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea listResultSet;
    private java.awt.List listTabelas;
    private javax.swing.JTable tableResultSet;
    private javax.swing.JTextField tfQuery;
    // End of variables declaration//GEN-END:variables

}
