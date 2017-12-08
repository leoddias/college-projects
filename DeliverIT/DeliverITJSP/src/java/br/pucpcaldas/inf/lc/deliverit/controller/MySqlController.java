package br.pucpcaldas.inf.lc.deliverit.controller;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlController {

    private Connection conn = null;    

    public MySqlController() throws ClassNotFoundException, SQLException {
     
            Class.forName("com.mysql.jdbc.Driver");
            String serverName = "localhost"; 
            String mydatabase = "deliverit"; 
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = "root"; 
            String password = "177013aew"; 
            conn = (Connection) DriverManager.getConnection(url, username, password);

    }

    public Connection getConn() {
        return conn;
    } 
    
    public void Fechar() {
        try {
            conn.close();            
            conn = null;
        } catch (SQLException ex) {
            Logger.getLogger(MySqlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isValid()
    {
        boolean value = false;        
        try {
            value = conn.isValid(5);
        } catch (SQLException ex) {
            Logger.getLogger(MySqlController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }


}
