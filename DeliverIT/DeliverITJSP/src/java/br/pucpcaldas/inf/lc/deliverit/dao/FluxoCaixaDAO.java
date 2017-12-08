package br.pucpcaldas.inf.lc.deliverit.dao;

import br.pucpcaldas.inf.lc.deliverit.controller.MySqlController;
import br.pucpcaldas.inf.lc.deliverit.model.FluxoCaixa;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FluxoCaixaDAO {

    private MySqlController conn;

    public FluxoCaixaDAO(MySqlController conexao) {
        if (!conexao.isValid() || conexao == null) {
            try {
                conexao = new MySqlController();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        conn = conexao;
    }

    public void criarRegistro(Timestamp data, String descricao, boolean movimentacao, float valor) { //CREATE
        try {
            PreparedStatement stmt = conn.getConn().
                    prepareStatement("INSERT INTO `cad_fluxocaixa`(`data`, `movimentacao`, `valor`, `descricao`) VALUES ('" + data + "', " + movimentacao + ", " + Float.toString(valor) + ", '" + descricao + "')");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public FluxoCaixa[] carregaRegistros(String data) {
        FluxoCaixa retorno[] = new FluxoCaixa[1];
        PreparedStatement stmt;
        ResultSet rs;
        int i = 0;       
        String query = "SELECT COUNT(*) FROM cad_fluxocaixa WHERE DATE(data) ='"+data+"';";

        try {
            stmt = conn.getConn().prepareStatement(query);
            rs = stmt.executeQuery();

            rs.next();

            retorno = new FluxoCaixa[rs.getInt(1)];
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(MySqlController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        query = "SELECT * FROM cad_fluxocaixa WHERE DATE(data) ='"+data+"' ORDER BY data DESC;";
        try {
            stmt = conn.getConn().prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                retorno[i] = new FluxoCaixa(rs.getString("descricao"), rs.getTimestamp("data"),
                        rs.getBoolean("movimentacao"), rs.getFloat("valor"));
                i++;
            }
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(MySqlController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public String[] carregaDatas() {
        String retorno[] = new String[1];
        PreparedStatement stmt;
        ResultSet rs;
        int i = 0;       
        String query = "SELECT COUNT(distinct DATE(data)) FROM cad_fluxocaixa;";

        try {
            stmt = conn.getConn().prepareStatement(query);
            rs = stmt.executeQuery();
            rs.next();
            retorno = new String[rs.getInt(1)];
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(MySqlController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        query = "SELECT distinct DATE(data) FROM cad_fluxocaixa ORDER BY data DESC;";
        try {
            stmt = conn.getConn().prepareStatement(query);
            rs = stmt.executeQuery();
            Date date;
            SimpleDateFormat sdf;           

            while (rs.next()) {
                /*date = new Date(rs.getTimestamp("DATE(data)").getTime());
                sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                retorno[i] = new String(sdf.format(date));*/
                retorno[i] = new String(rs.getString("DATE(data)"));
                i++;
            }
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(MySqlController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
        public Timestamp getData()
    {
        return new Timestamp(Calendar.getInstance().getTime().getTime());
    }

}
