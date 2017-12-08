package br.pucpcaldas.inf.lc.deliverit.dao;

import br.pucpcaldas.inf.lc.deliverit.controller.MySqlController;
import br.pucpcaldas.inf.lc.deliverit.model.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriaDAO {

    private MySqlController conn;

    public CategoriaDAO(MySqlController conexao) {
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

    public String consultaCategoria(int codCategoria) {

        String query = "SELECT nomeCategoria FROM cad_categoria WHERE codCategoria='" + codCategoria + "'";
        PreparedStatement stmt;
        try {
            stmt = conn.getConn().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            String nome = rs.getString("nomeCategoria");
            stmt.close();
            return nome;
        } catch (SQLException ex) {
            Logger.getLogger(MySqlController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "null";

    }

    public Categoria[] carregaCategorias() {

        Categoria retorno[] = new Categoria[1];
        PreparedStatement stmt;
        ResultSet rs;
        int i = 0;
        String query = "SELECT COUNT(*) FROM cad_categoria";

        try {
            stmt = conn.getConn().prepareStatement(query);
            rs = stmt.executeQuery();

            rs.next();

            retorno = new Categoria[rs.getInt(1)];
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(MySqlController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        query = "SELECT * FROM cad_categoria";
        try {
            stmt = conn.getConn().prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                retorno[i] = new Categoria(rs.getString("nomeCategoria"), rs.getInt("codCategoria"));
                i++;
            }
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(MySqlController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public void atualizaCategoria(String novoNome, int codigoCategoria) { //UPDATE
        try {
            String instrucao = "UPDATE `cad_categoria` SET `nomeCategoria`= '" + novoNome + "' WHERE codCategoria = '" + codigoCategoria + "'";
            PreparedStatement stmt;

            stmt = conn.getConn().prepareStatement(instrucao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void criarCategoria(String nomeCategoria) { //CREATE
        try {
            PreparedStatement stmt = conn.getConn()
                    .prepareStatement("INSERT INTO cad_categoria(nomeCategoria) VALUES ('" + nomeCategoria + "')");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerCategoria(int codCategoria) { //DELETE
        try {
            PreparedStatement preparedStatement = conn.getConn()
                    .prepareStatement("DELETE FROM cad_categoria WHERE codCategoria= " + codCategoria);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
