package br.pucpcaldas.inf.lc.deliverit.controller;

import br.pucpcaldas.inf.lc.deliverit.dao.ClienteDAO;
import br.pucpcaldas.inf.lc.deliverit.dao.FluxoCaixaDAO;
import br.pucpcaldas.inf.lc.deliverit.dao.PedidoDAO;
import br.pucpcaldas.inf.lc.deliverit.model.Pedido;
import br.pucpcaldas.inf.lc.deliverit.model.Produto;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PedidoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        MySqlController conexao = (MySqlController) session.getAttribute("conexao");
        PedidoDAO pedidodao = new PedidoDAO(conexao);
        ClienteDAO clientedao = new ClienteDAO(conexao);
        RequestDispatcher rd;

        switch (request.getParameter("button").toString()) {
            case "alterar":
                pedidodao.atualizaStatus(request.getParameter("alterarCombo"), Integer.parseInt(request.getParameter("codPedido").toString()));
                if (request.getParameter("alterarCombo").toString().equals("Fechado")) {
                    rd = getServletContext().getRequestDispatcher("/autenticado/confirmacaoPedido.jsp");
                    Produto listaProdutos[] = pedidodao.carregaItemPedido(Integer.parseInt(request.getParameter("codPedido")));
                    Pedido pedido[] = pedidodao.buscaPedido(Integer.parseInt(request.getParameter("codPedido").toString()));
                    String produtos = "";
                    float valorProdutos = 0;
                    for (Produto prod : listaProdutos) {
                        produtos += prod.getNomeProduto() + ", ";
                        valorProdutos += prod.getPrecoProduto()*pedidodao.carregaQtdPedido(Integer.parseInt(request.getParameter("codPedido").toString()), prod.getCodProduto());
                    }
                    request.setAttribute("valorProdutos", valorProdutos);
                    request.setAttribute("valorDesconto", pedido[0].getValorDesconto());
                    request.setAttribute("valorEntrega", pedido[0].getValorEntrega());
                    request.setAttribute("valorTotal", pedido[0].getValorTotal());
                    request.setAttribute("produtos", produtos);
                    request.setAttribute("nomeCliente", clientedao.buscaCliente(pedido[0].getCodCliente()).getNomeCliente());
                    request.setAttribute("rotaChegada", clientedao.buscaCliente(pedido[0].getCodCliente()).getEnderecoCliente());
                    FluxoCaixaDAO registrodao = new FluxoCaixaDAO(conexao);
                    registrodao.criarRegistro(registrodao.getData(), "Pedido Nº " + request.getParameter("codPedido"), true, pedido[0].getValorTotal());
                    rd.forward(request, response);
                } else {
                    response.sendRedirect("index.jsp");
                }
                break;

            case "listar":
                request.setAttribute("tipoConsulta", "todos");
                rd = getServletContext().getRequestDispatcher("/autenticado/index.jsp");
                rd.forward(request, response);
                break;
            case "buscar":
                request.setAttribute("tipoConsulta", "buscar");
                rd = getServletContext().getRequestDispatcher("/autenticado/index.jsp");
                if (!request.getParameter("codPedido").isEmpty()) {
                    request.setAttribute("codBusca", request.getParameter("codPedido"));
                    rd.forward(request, response);
                } else {
                    response.sendRedirect("index.jsp");
                }
                break;

        }

    }
}
