package br.pucpcaldas.inf.lc.deliverit.dao;

import br.pucpcaldas.inf.lc.deliverit.controller.MySqlController;
import br.pucpcaldas.inf.lc.deliverit.model.Pedido;
import br.pucpcaldas.inf.lc.deliverit.model.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoDAO {

    private MySqlController conn;

    public PedidoDAO(MySqlController conexao) {
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

    public Pedido[] carregaPedidos(String status) {
        Pedido retorno[] = new Pedido[1];
        PreparedStatement stmt;
        ResultSet rs;
        int i = 0;
        String query;
        if (status.equals("todos")) {
            query = "SELECT COUNT(*) FROM mov_pedido";
        } else {
            query = "SELECT COUNT(*) FROM mov_pedido WHERE statusPedido!='Fechado'";
        }

        try {
            stmt = conn.getConn().prepareStatement(query);
            rs = stmt.executeQuery();

            rs.next();

            retorno = new Pedido[rs.getInt(1)];
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(MySqlController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        if (status.equals("todos")) {
            query = "SELECT * FROM mov_pedido order by dataPedido ASC";

        } else {
            query = "SELECT * FROM mov_pedido WHERE statusPedido!='Fechado' order by dataPedido ASC";

        }

        try {
            stmt = conn.getConn().prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                retorno[i] = new Pedido(rs.getInt("codPedido"), rs.getFloat("valorTotal"), rs.getFloat("valorEntrega"), rs.getFloat("valorDesconto"),
                        rs.getInt("codCliente"), rs.getInt("codEstabelecimento"), rs.getTimestamp("dataPedido"), rs.getString("statusPedido"));
                i++;
            }
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(MySqlController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Pedido[] buscaPedido(int codBusca) {
        Pedido retorno[] = new Pedido[1];
        PreparedStatement stmt;
        ResultSet rs;
        String query = "SELECT * FROM mov_pedido WHERE codPedido=" + codBusca;
        try {
            stmt = conn.getConn().prepareStatement(query);
            rs = stmt.executeQuery();
            rs.next();

            retorno[0] = new Pedido(rs.getInt("codPedido"), rs.getFloat("valorTotal")-rs.getFloat("valorDesconto"), rs.getFloat("valorEntrega"), rs.getFloat("valorDesconto"),
                    rs.getInt("codCliente"), rs.getInt("codEstabelecimento"), rs.getTimestamp("dataPedido"), rs.getString("statusPedido"));

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(MySqlController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public int carregaQtdPedido(int codPedido, int codProduto) {
        int retorno = 0;
        PreparedStatement stmt;
        ResultSet rs;

        String query = "SELECT * FROM mov_pedido_item WHERE codPedido=" + codPedido + " AND codProduto=" + codProduto;
        System.out.println("QUERY " + query);
        try {
            stmt = conn.getConn().prepareStatement(query);
            rs = stmt.executeQuery();
            rs.next();
            retorno = rs.getInt("qtdProduto");
            System.out.println("retorno: " + retorno);

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(MySqlController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;

    }

    public Produto[] carregaItemPedido(int codPedido) {

        Produto retorno[] = new Produto[1];
        PreparedStatement stmt;
        ResultSet rs;
        int i = 0;
        String query = "SELECT COUNT(*) FROM mov_pedido_item WHERE codPedido=" + codPedido;

        try {
            stmt = conn.getConn().prepareStatement(query);
            rs = stmt.executeQuery();

            rs.next();

            retorno = new Produto[rs.getInt(1)];
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(MySqlController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        query = "SELECT * FROM mov_pedido_item WHERE codPedido=" + codPedido;
        try {
            stmt = conn.getConn().prepareStatement(query);
            rs = stmt.executeQuery();
            ProdutoDAO produtodao = new ProdutoDAO(conn);
            while (rs.next()) {
                retorno[i] = produtodao.consultaProduto(rs.getInt("codProduto"));
                i++;
            }
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(MySqlController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public void atualizaStatus(String novoStatus, int codPedido) { //UPDATE
        try {
            String instrucao = "UPDATE `mov_pedido` SET `statusPedido`= '" + novoStatus + "' WHERE codPedido = '" + codPedido + "'";
            PreparedStatement stmt;
            stmt = conn.getConn().prepareStatement(instrucao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
