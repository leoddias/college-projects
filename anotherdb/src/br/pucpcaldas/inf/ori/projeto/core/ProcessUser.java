package br.pucpcaldas.inf.ori.projeto.core;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProcessUser {

    ArrayList<User> usuario = new ArrayList<>();
    private User userBuffer;
    private String[] bufferUser;
    private File arquivo;
    private FileInputStream conexao;
    private DataInputStream acessoArquivo;

    public ProcessUser() {
        try {
            this.arquivo = new File("USERS.TABLE");
            this.conexao = new FileInputStream(arquivo);
            this.acessoArquivo = new DataInputStream(conexao);    
            while (acessoArquivo.available() != 0) {
                bufferUser = acessoArquivo.readLine().split(",");
                userBuffer = new User(bufferUser[1], bufferUser[2], Boolean.parseBoolean(bufferUser[3]), Boolean.parseBoolean(bufferUser[4]),
                        Boolean.parseBoolean(bufferUser[5]), Boolean.parseBoolean(bufferUser[6]), Boolean.parseBoolean(bufferUser[7]));
                usuario.add(userBuffer);         
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO PROCESSAR USUARIOS!");
            return;
        }

    }

    public boolean checkUser(String account) {
        for (User usuario1 : usuario) {
            if (usuario1.getAccount().equals(account))
                return true;
        }
        return false;
    }

}
