package br.pucpcaldas.inf.ori.projeto.gui;

import br.pucpcaldas.inf.ori.projeto.core.Hora;
import br.pucpcaldas.inf.ori.projeto.core.ProcessUser;
import br.pucpcaldas.inf.ori.projeto.core.Session;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Login extends javax.swing.JFrame {

    ProcessUser usuario;

    public Login() {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
        initComponents();
        jtPassword.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                try {
                    usuario = new ProcessUser();
                    if (usuario.checkUser(jtfLogin.getText()) == true) {
                        jlAuth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/pucpcaldas/inf/ori/projeto/images/success-icon.png")));
                        imagemLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/pucpcaldas/inf/ori/projeto/images/" + jtfLogin.getText() + ".png"))); // NOI18N
                    } else {
                        jlAuth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/pucpcaldas/inf/ori/projeto/images/error-icon.png")));
                        imagemLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/pucpcaldas/inf/ori/projeto/images/login-null.png"))); // NOI18N
                    }
                } catch (Exception error) {
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        jtfLogin.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                jlAuth.setIcon(null);
                imagemLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/pucpcaldas/inf/ori/projeto/images/login-null.png"))); // NOI18N

            }

            @Override
            public void focusLost(FocusEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtfLogin = new javax.swing.JTextField();
        jtPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlAuth = new javax.swing.JLabel();
        imagemLogin = new javax.swing.JLabel();
        jbLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AnotherDB - Login");
        setBackground(new java.awt.Color(51, 51, 51));
        setLocation(new java.awt.Point(300, 150));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enter(evt);
            }
        });

        jtfLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jtfLogin.setToolTipText("Username");
        jtfLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfLoginActionPerformed(evt);
            }
        });
        jtfLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfLoginKeyReleased(evt);
            }
        });

        jtPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jtPassword.setToolTipText("Password");
        jtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtPasswordActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/pucpcaldas/inf/ori/projeto/images/login-icon.fw.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/pucpcaldas/inf/ori/projeto/images/pass-icon.fw.png"))); // NOI18N

        imagemLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/pucpcaldas/inf/ori/projeto/images/login-null.png"))); // NOI18N

        jbLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/pucpcaldas/inf/ori/projeto/images/login-button.fw.png"))); // NOI18N
        jbLogin.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jbLoginMouseDragged(evt);
            }
        });
        jbLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbLoginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbLoginMouseExited(evt);
            }
        });
        jbLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbLoginKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 3, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbLogin)
                            .addComponent(jlAuth, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imagemLogin)
                        .addGap(16, 16, 16)))
                .addGap(3, 3, 3))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(imagemLogin)
                .addGap(0, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jtfLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtPassword))
                        .addGap(5, 5, 5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlAuth, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbLogin))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jtfLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfLoginActionPerformed

    }//GEN-LAST:event_jtfLoginActionPerformed

    private void jtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtPasswordActionPerformed
    }//GEN-LAST:event_jtPasswordActionPerformed

    private void jbLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbLoginMouseClicked
        String permission = "";
        Process p;
        MessageDigest md5Aux;
        String password = jtPassword.getText();
        File arquivo;
        FileInputStream conexao;
        DataInputStream acessoArquivo;
        char buffer;

        if (password.isEmpty() || jtfLogin.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "USUARIO OU SENHA NÃO PODE ESTAR VAZIO!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            md5Aux = MessageDigest.getInstance("MD5");
            md5Aux.update(password.getBytes(), 0, password.length());
            BigInteger i = new BigInteger(1, md5Aux.digest());
            password = String.format("%1$032X", i);
            System.out.println("MD5 = " + password);
            p = Runtime.getRuntime().exec("USER.EXE AUTH " + jtfLogin.getText().toUpperCase() + " " + password);

            Thread.sleep(1000);

            switch (p.exitValue()) {
                case 0:
                    JOptionPane.showMessageDialog(rootPane, "ERROR\nARGUMENTOS EXCEDIDOS!");
                    break;

                case 1:
                    arquivo = new File("ResultSet.txt");
                    conexao = new FileInputStream(arquivo);
                    acessoArquivo = new DataInputStream(conexao);
                    while (acessoArquivo.available() != 0) {
                        buffer = (char) acessoArquivo.read();
                        if (buffer != ',') {
                            permission += buffer;
                        }
                    }
                    for (int x = 0; x < permission.length(); x++) {

                        switch (x) {
                            case 0:

                                if (permission.charAt(x) == '1') {
                                    Session.canCreate = true;
                                    //System.out.println("YOU CAN CREATE");
                                }
                                break;
                            case 1:
                                if (permission.charAt(x) == '1') {
                                    Session.canDelete = true;
                                    //System.out.println("YOU CAN DELETE");
                                }
                                break;
                            case 2:
                                if (permission.charAt(x) == '1') {
                                    Session.canInsert = true;
                                    // System.out.println("YOU CAN INSERT");
                                }
                                break;
                            case 3:
                                if (permission.charAt(x) == '1') {
                                    Session.canSelect = true;
                                    // System.out.println("YOU CAN SELECT");
                                }
                                break;
                            case 4:
                                if (permission.charAt(x) == '1') {
                                    Session.canUpdate = true;
                                    // System.out.println("YOU CAN Update");
                                }
                                break;
                        }
                    }

//                    String cryptKey = JOptionPane.showInputDialog("Insira a chave para a descriptografia");
//                    if(cryptKey.equals("1")){
                    Session.userName = jtfLogin.getText().toUpperCase();
                    Session.autenticado = true;
//                    }                    
                    break;

                case 2:
                    JOptionPane.showMessageDialog(rootPane, "USUARIO/SENHA INVALIDA!", "ERRO", JOptionPane.ERROR_MESSAGE);
                    break;

                case -1:
                    JOptionPane.showMessageDialog(rootPane, "NAO FOI POSSIVEL ABRIR USERS.TABLE", "ERRO", JOptionPane.ERROR_MESSAGE);
                    break;

            }
        } catch (NoSuchAlgorithmException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar senha MD5");
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Erro no THREAD.SLEEP");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "ERRO DURANTE A REQUISIÇÃO DE AUTENTICAÇÃO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        if (Session.autenticado) {
            Hora h = new Hora();
            JOptionPane.showMessageDialog(rootPane, "Usuário autenticado com sucesso!");
            try {
                p = Runtime.getRuntime().exec("DECRYPTER.exe");
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.dispose();
            Session.horaLogin = h.getHora();

            new FrameEscolhaTipoBanco().setVisible(true);
        }


    }//GEN-LAST:event_jbLoginMouseClicked

    private void jbLoginMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbLoginMouseDragged

    }//GEN-LAST:event_jbLoginMouseDragged

    private void jbLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbLoginMouseEntered
        jbLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/pucpcaldas/inf/ori/projeto/images/login-button2.fw.png")));
    }//GEN-LAST:event_jbLoginMouseEntered

    private void jbLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbLoginMouseExited
        jbLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/pucpcaldas/inf/ori/projeto/images/login-button.fw.png")));
    }//GEN-LAST:event_jbLoginMouseExited

    private void enter(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enter

    }//GEN-LAST:event_enter

    private void jbLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbLoginKeyPressed

    }//GEN-LAST:event_jbLoginKeyPressed

    private void jtfLoginKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfLoginKeyReleased
        jtfLogin.setText(jtfLogin.getText().toUpperCase());
    }//GEN-LAST:event_jtfLoginKeyReleased

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imagemLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jbLogin;
    private javax.swing.JLabel jlAuth;
    private javax.swing.JPasswordField jtPassword;
    private javax.swing.JTextField jtfLogin;
    // End of variables declaration//GEN-END:variables
}
