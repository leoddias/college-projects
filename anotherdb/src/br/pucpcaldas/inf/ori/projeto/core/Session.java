package br.pucpcaldas.inf.ori.projeto.core;
import java.io.File;
import javax.swing.JOptionPane;

/*
    ORI

*/


public class Session {
    
    public static String userName;
    public static boolean canCreate = false;
    public static boolean canInsert = false;
    public static boolean canUpdate = false;
    public static boolean canSelect = false;
    public static boolean canDelete = false;
    
    public static boolean autenticado = false;
    public static String banco="";
    public static String dirTabelas ="Tabelas\\";
    public static String dirExecutaveis ="";
    public static String userDir= System.getProperty("user.dir");
    public static String horaLogin="";
//    public static String dirTabelas ="C:\\Users\\OCTAVIO-NB\\Documents\\NetBeansProjects\\AnotherDB\\Tabelas\\";
//    public static String dirExecutaveis ="C:\\Users\\OCTAVIO-NB\\Documents\\NetBeansProjects\\AnotherDB\\";
    public static String tabelaAtual="";
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    public static int createDatabase(String databaseName)
    {
        File aux = new File(userDir+"\\tabelas\\"+databaseName);
        if(aux.mkdir())
           return(1);
        
        return(0);    
    }
    
}
