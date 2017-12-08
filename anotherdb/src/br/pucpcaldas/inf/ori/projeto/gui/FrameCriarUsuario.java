package br.pucpcaldas.inf.ori.projeto.gui;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FrameCriarUsuario extends javax.swing.JFrame {

    public FrameCriarUsuario() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtfUsuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jtPassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jcbCreate = new javax.swing.JCheckBox();
        jcbInsert = new javax.swing.JCheckBox();
        jcbUpdate = new javax.swing.JCheckBox();
        jcbSelect = new javax.swing.JCheckBox();
        jcbDelete = new javax.swing.JCheckBox();
        jbClean = new javax.swing.JButton();

        setTitle("AnotherDB - Criar Usuario");

        jLabel1.setText("Login:");

        jLabel2.setText("Password:");

        jPanel1.setMinimumSize(new java.awt.Dimension(175, 175));
        jPanel1.setPreferredSize(new java.awt.Dimension(175, 175));
        jPanel1.setRequestFocusEnabled(false);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/pucpcaldas/inf/ori/projeto/images/newUser.fw.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButton1.setText("Criar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Permissões"));

        jcbCreate.setText("Criar");

        jcbInsert.setText("Inserir");

        jcbUpdate.setText("Atualizar");

        jcbSelect.setText("Consultar");

        jcbDelete.setText("Deletar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbCreate)
                    .addComponent(jcbInsert)
                    .addComponent(jcbUpdate)
                    .addComponent(jcbSelect)
                    .addComponent(jcbDelete))
                .addGap(0, 19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jcbCreate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbInsert)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbSelect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jcbDelete)
                .addContainerGap())
        );

        jbClean.setText("Limpar Campos");
        jbClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCleanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfUsuario)
                    .addComponent(jLabel1)
                    .addComponent(jtPassword)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbClean)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(4, 4, 4)
                                .addComponent(jtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1)
                                .addComponent(jbClean)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCleanActionPerformed
        jtPassword.setText("");
        jtfUsuario.setText("");
               
    }//GEN-LAST:event_jbCleanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jtfUsuario.getText().isEmpty() || jtPassword.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Usuario/Senha Vazio");
            return;
        }
        String pass = jtPassword.getText();
        MessageDigest md5Aux;
        try {
            md5Aux = MessageDigest.getInstance("MD5");
                        md5Aux.update(pass.getBytes(), 0, pass.length());
            BigInteger i = new BigInteger(1, md5Aux.digest());
            pass = String.format("%1$032X", i);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FrameCriarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            String queryAux = "USER CREATE "+jtfUsuario.getText().toUpperCase()+" "+pass+" "+
                    Boolean.compare(jcbCreate.isSelected(),false)+" "+Boolean.compare(jcbInsert.isSelected(),false)+
                    " "+Boolean.compare(jcbUpdate.isSelected(),false)+" "+Boolean.compare(jcbSelect.isSelected(),false)+
                    " "+Boolean.compare(jcbDelete.isSelected(),false);
            
           System.out.println(queryAux);
           Process p = Runtime.getRuntime().exec(queryAux);
           Thread.sleep(1000);
           
           switch(p.exitValue())
           {
               case 1:
                   JOptionPane.showMessageDialog(rootPane, "Usuario "+jtfUsuario.getText().toUpperCase()+" CRIADO!");
                   break;
               case 2:
                   JOptionPane.showMessageDialog(rootPane, "Usuario "+jtfUsuario.getText().toUpperCase()+" JA EXISTE!");
                   break;
               default:
                   JOptionPane.showMessageDialog(rootPane, "ERROR IN USER.EXE");
                   break;                           
                           
           }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "ERROR AO EXECUTAR USER.EXE");
        } catch (InterruptedException ex) {
            Logger.getLogger(FrameCriarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

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
            java.util.logging.Logger.getLogger(FrameCriarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameCriarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameCriarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameCriarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameCriarUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbClean;
    private javax.swing.JCheckBox jcbCreate;
    private javax.swing.JCheckBox jcbDelete;
    private javax.swing.JCheckBox jcbInsert;
    private javax.swing.JCheckBox jcbSelect;
    private javax.swing.JCheckBox jcbUpdate;
    private javax.swing.JPasswordField jtPassword;
    private javax.swing.JTextField jtfUsuario;
    // End of variables declaration//GEN-END:variables
}
