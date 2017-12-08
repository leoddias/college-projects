package br.pucpcaldas.inf.lc.deliverit.dao;

import br.pucpcaldas.inf.lc.deliverit.controller.MySqlController;
import br.pucpcaldas.inf.lc.deliverit.model.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {

    public MySqlController conn;

    public ClienteDAO(MySqlController conexao) {
        if (!conexao.isValid() || conexao == null) {
            try {
                conexao = new MySqlController();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.conn = conexao;
    }

    public String consultaCliente(int codCliente) {

        String query = "SELECT nomeCliente FROM cad_cliente WHERE codCliente='" + codCliente + "'";
        PreparedStatement stmt;
        try {
            stmt = conn.getConn().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String nome = rs.getString("nomeCliente");
            stmt.close();
            return nome;
        } catch (SQLException ex) {
            Logger.getLogger(MySqlController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "null";
    }
        public Cliente buscaCliente(int codCliente) {
        Cliente cliente = null;
        String query = "SELECT * FROM cad_cliente WHERE codCliente='" + codCliente + "'";
        PreparedStatement stmt;
        try {
            stmt = conn.getConn().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            cliente = new Cliente(codCliente, rs.getString("nomeCliente"), rs.getString("enderecoCliente"));            
            stmt.close();
            return cliente;
        } catch (SQLException ex) {
            Logger.getLogger(MySqlController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cliente;
    }
}
