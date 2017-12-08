package projetocalendario;

import javax.swing.JOptionPane;

public class Main extends javax.swing.JFrame {


    public Main() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaMes = new javax.swing.JTable();
        botaoProximo = new javax.swing.JButton();
        botaoAnterior = new javax.swing.JButton();
        textoAno = new java.awt.TextField();
        botaoOk = new javax.swing.JButton();
        labelDigiteAno = new javax.swing.JLabel();
        labelBissexto = new javax.swing.JLabel();
        labelFeriados = new javax.swing.JLabel();
        labelNomeMes = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textoFeriado = new javax.swing.JTextArea();
        labelTotalFeriadosAno = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        totalDiasUteis = new javax.swing.JLabel();
        totalFeriados = new javax.swing.JLabel();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calendário");

        tabelaMes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"
            }
        ));
        jScrollPane1.setViewportView(tabelaMes);

        botaoProximo.setText(">>");
        botaoProximo.setToolTipText("");
        botaoProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoProximoActionPerformed(evt);
            }
        });

        botaoAnterior.setText("<<");
        botaoAnterior.setToolTipText("");
        botaoAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAnteriorActionPerformed(evt);
            }
        });

        textoAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoAnoActionPerformed(evt);
            }
        });

        botaoOk.setText("Ok");
        botaoOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoOkActionPerformed(evt);
            }
        });

        labelDigiteAno.setText("Digite o Ano:");

        labelBissexto.setText("Aguardando....");

        labelFeriados.setText("Feriados do Ano:");

        labelNomeMes.setText("Mes");

        textoFeriado.setColumns(20);
        textoFeriado.setRows(5);
        jScrollPane3.setViewportView(textoFeriado);

        labelTotalFeriadosAno.setText("Aguardando...");

        jLabel1.setText("Dias Uteis:");

        jLabel2.setText("Feriados:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelFeriados)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelTotalFeriadosAno)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botaoAnterior)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoProximo))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelNomeMes)
                                .addGap(65, 65, 65)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalDiasUteis)
                                .addGap(17, 17, 17)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalFeriados)))
                        .addGap(90, 90, 90))))
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(labelDigiteAno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelBissexto)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textoAno, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(botaoOk)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoOk, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDigiteAno, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(3, 3, 3)
                        .addComponent(labelBissexto)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelNomeMes)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(totalDiasUteis)
                                    .addComponent(totalFeriados))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(botaoProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(botaoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFeriados)
                    .addComponent(labelTotalFeriadosAno, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoProximoActionPerformed
        try{
        Ano auxAno = new Ano(Integer.parseInt(textoAno.getText()));
        for(int i=0;i<=5;i++)
        {
            for(int j=0; j<=6;j++)
                tabelaMes.setValueAt("", i, j);
        }
        int auxProximo=0;
        if(labelNomeMes.getText().equals("Janeiro"))
            auxProximo=1;
        if(labelNomeMes.getText().equals("Fevereiro"))
            auxProximo=2;
        if(labelNomeMes.getText().equals("Março"))
            auxProximo=3;
        if(labelNomeMes.getText().equals("Abril"))
            auxProximo=4;
        if(labelNomeMes.getText().equals("Maio"))
            auxProximo=5;
        if(labelNomeMes.getText().equals("Junho"))
            auxProximo=6;
        if(labelNomeMes.getText().equals("Julho"))
            auxProximo=7;
        if(labelNomeMes.getText().equals("Agosto"))
            auxProximo=8;
        if(labelNomeMes.getText().equals("Setembro"))
            auxProximo=9;
        if(labelNomeMes.getText().equals("Outubro"))
            auxProximo=10;
        if(labelNomeMes.getText().equals("Novembro"))
            auxProximo=11;
        
        labelNomeMes.setText(auxAno.mesAno[auxProximo].getNomeMes());
        
        int auxColuna=0;
        if(auxAno.mesAno[auxProximo].diaMes[0].getNomeDia().equals("Domingo"))
            auxColuna=0;
        if(auxAno.mesAno[auxProximo].diaMes[0].getNomeDia().equals("Segunda"))
            auxColuna=1;
        if(auxAno.mesAno[auxProximo].diaMes[0].getNomeDia().equals("Terça"))
            auxColuna=2;
        if(auxAno.mesAno[auxProximo].diaMes[0].getNomeDia().equals("Quarta"))
            auxColuna=3;
        if(auxAno.mesAno[auxProximo].diaMes[0].getNomeDia().equals("Quinta"))
            auxColuna=4;
        if(auxAno.mesAno[auxProximo].diaMes[0].getNomeDia().equals("Sexta"))
            auxColuna=5;
        if(auxAno.mesAno[auxProximo].diaMes[0].getNomeDia().equals("Sabado"))
            auxColuna=6;
       
        int feriadoMes=0;
        for(int aux=0;aux<auxAno.mesAno[auxProximo].getQtdDia();aux++)
        {
            if(auxAno.mesAno[auxProximo].diaMes[aux] instanceof Feriado)
                feriadoMes++;
        }
        
        totalFeriados.setText(String.valueOf(feriadoMes));
        
        int utilMes=0;
        for(int aux=0;aux<auxAno.mesAno[auxProximo].getQtdDia();aux++)
        {
            if(auxAno.mesAno[auxProximo].diaMes[aux] instanceof DiaUtil)
                utilMes++;
        }
        totalDiasUteis.setText(String.valueOf(utilMes));
        
        for(int auxLinha=0,auxDia=1;auxLinha<6;auxLinha++,auxColuna=0)
        {
            for(;auxColuna<=6;auxColuna++,auxDia++)
            {
                if(auxDia<=auxAno.mesAno[auxProximo].getQtdDia())
                    tabelaMes.setValueAt(auxDia, auxLinha, auxColuna);
            }
        }
        
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Erro! Verifique se o ano está correto!");
        }
    }//GEN-LAST:event_botaoProximoActionPerformed

    private void botaoAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAnteriorActionPerformed
        try{
        Ano auxAno = new Ano(Integer.parseInt(textoAno.getText()));
        for(int i=0;i<=5;i++)
        {
            for(int j=0; j<=6;j++)
                tabelaMes.setValueAt("", i, j);
        }
        int auxAnterior=0;
        if(labelNomeMes.getText().equals("Janeiro"))
            auxAnterior=11;
        if(labelNomeMes.getText().equals("Fevereiro"))
            auxAnterior=0;
        if(labelNomeMes.getText().equals("Março"))
            auxAnterior=1;
        if(labelNomeMes.getText().equals("Abril"))
            auxAnterior=2;
        if(labelNomeMes.getText().equals("Maio"))
            auxAnterior=3;
        if(labelNomeMes.getText().equals("Junho"))
            auxAnterior=4;
        if(labelNomeMes.getText().equals("Julho"))
            auxAnterior=5;
        if(labelNomeMes.getText().equals("Agosto"))
            auxAnterior=6;
        if(labelNomeMes.getText().equals("Setembro"))
            auxAnterior=7;
        if(labelNomeMes.getText().equals("Outubro"))
            auxAnterior=8;
        if(labelNomeMes.getText().equals("Novembro"))
            auxAnterior=9;
        if(labelNomeMes.getText().equals("Dezembro"))
            auxAnterior=10;
        
        labelNomeMes.setText(auxAno.mesAno[auxAnterior].getNomeMes());
        
        int auxColuna=0;
        if(auxAno.mesAno[auxAnterior].diaMes[0].getNomeDia().equals("Domingo"))
            auxColuna=0;
        if(auxAno.mesAno[auxAnterior].diaMes[0].getNomeDia().equals("Segunda"))
            auxColuna=1;
        if(auxAno.mesAno[auxAnterior].diaMes[0].getNomeDia().equals("Terça"))
            auxColuna=2;
        if(auxAno.mesAno[auxAnterior].diaMes[0].getNomeDia().equals("Quarta"))
            auxColuna=3;
        if(auxAno.mesAno[auxAnterior].diaMes[0].getNomeDia().equals("Quinta"))
            auxColuna=4;
        if(auxAno.mesAno[auxAnterior].diaMes[0].getNomeDia().equals("Sexta"))
            auxColuna=5;
        if(auxAno.mesAno[auxAnterior].diaMes[0].getNomeDia().equals("Sabado"))
            auxColuna=6;
       
        
        for(int auxLinha=0,auxDia=1;auxLinha<6;auxLinha++,auxColuna=0)
        {
            for(;auxColuna<=6;auxColuna++,auxDia++)
            {
                if(auxDia<=auxAno.mesAno[auxAnterior].getQtdDia())
                    tabelaMes.setValueAt(auxDia, auxLinha, auxColuna);
            }
        }
        int feriadoMes=0;
        for(int aux=0;aux<auxAno.mesAno[auxAnterior].getQtdDia();aux++)
        {
            if(auxAno.mesAno[auxAnterior].diaMes[aux] instanceof Feriado)
                feriadoMes++;
        }
        
        totalFeriados.setText(String.valueOf(feriadoMes));
        
        int utilMes=0;
        for(int aux=0;aux<auxAno.mesAno[auxAnterior].getQtdDia();aux++)
        {
            if(auxAno.mesAno[auxAnterior].diaMes[aux] instanceof DiaUtil)
                utilMes++;
        }
        totalDiasUteis.setText(String.valueOf(utilMes));
        
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Erro! Verifique se o ano está correto!");
        }
    }//GEN-LAST:event_botaoAnteriorActionPerformed

    private void textoAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoAnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoAnoActionPerformed

    private void botaoOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoOkActionPerformed
        try {
            Ano auxAno = new Ano(Integer.parseInt(textoAno.getText()));
            if(auxAno.isBisexto())
                labelBissexto.setText("Ano Bissexto!");
            else
                labelBissexto.setText("Ano Não Bissexto!");
            
        labelNomeMes.setText("Janeiro");
        for(int i=0;i<=5;i++)
        {
            for(int j=0; j<=6;j++)
                tabelaMes.setValueAt("", i, j);
        }
        int auxColuna=0;
        if(auxAno.mesAno[0].diaMes[0].getNomeDia().equals("Domingo"))
            auxColuna=0;
        if(auxAno.mesAno[0].diaMes[0].getNomeDia().equals("Segunda"))
            auxColuna=1;
        if(auxAno.mesAno[0].diaMes[0].getNomeDia().equals("Terça"))
            auxColuna=2;
        if(auxAno.mesAno[0].diaMes[0].getNomeDia().equals("Quarta"))
            auxColuna=3;
        if(auxAno.mesAno[0].diaMes[0].getNomeDia().equals("Quinta"))
            auxColuna=4;
        if(auxAno.mesAno[0].diaMes[0].getNomeDia().equals("Sexta"))
            auxColuna=5;
        if(auxAno.mesAno[0].diaMes[0].getNomeDia().equals("Sabado"))
            auxColuna=6;
       
        int feriadoMes=0;
        for(int aux=0;aux<auxAno.mesAno[0].getQtdDia();aux++)
        {
            if(auxAno.mesAno[0].diaMes[aux] instanceof Feriado)
                feriadoMes++;
        }
        
        totalFeriados.setText(String.valueOf(feriadoMes));
        
        int utilMes=0;
        for(int aux=0;aux<auxAno.mesAno[0].getQtdDia();aux++)
        {
            if(auxAno.mesAno[0].diaMes[aux] instanceof DiaUtil)
                utilMes++;
        }
        totalDiasUteis.setText(String.valueOf(utilMes));
        
        for(int auxLinha=0,auxDia=1;auxLinha<5;auxLinha++,auxColuna=0)
        {
            for(;auxColuna<=6;auxColuna++,auxDia++)
            {
                if(auxDia<=auxAno.mesAno[0].getQtdDia())
                    tabelaMes.setValueAt(auxDia, auxLinha, auxColuna);
            }
        }
       
       int auxFeriados=0;
       for(int i=0; i<12; i++)
       {
           textoFeriado.append("Nome do Mês: " + auxAno.mesAno[i].getNomeMes()); 
           for(int j=0; j<auxAno.mesAno[i].getQtdDia(); j++)
           {
               if(auxAno.mesAno[i].diaMes[j] instanceof Feriado)
                   {
                       Feriado f = (Feriado) auxAno.mesAno[i].diaMes[j];
                       textoFeriado.append("\nDia : " + f.getNumDia() + " é feriado de: " + f.getNomeFeriado());
                       auxFeriados++;
                   }
            }
           textoFeriado.append("\n");
       } 
        labelTotalFeriadosAno.setText(String.valueOf(auxFeriados));
        textoFeriado.setCaretPosition(0);
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro! Verifique se o ano está correto!");
        } 
        
    }//GEN-LAST:event_botaoOkActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAnterior;
    private javax.swing.JButton botaoOk;
    private javax.swing.JButton botaoProximo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelBissexto;
    private javax.swing.JLabel labelDigiteAno;
    private javax.swing.JLabel labelFeriados;
    private javax.swing.JLabel labelNomeMes;
    private javax.swing.JLabel labelTotalFeriadosAno;
    private javax.swing.JTable tabelaMes;
    private java.awt.TextField textoAno;
    private javax.swing.JTextArea textoFeriado;
    private javax.swing.JLabel totalDiasUteis;
    private javax.swing.JLabel totalFeriados;
    // End of variables declaration//GEN-END:variables
}
