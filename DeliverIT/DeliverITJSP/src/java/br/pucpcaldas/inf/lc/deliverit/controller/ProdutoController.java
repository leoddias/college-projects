package br.pucpcaldas.inf.lc.deliverit.controller;

import br.pucpcaldas.inf.lc.deliverit.dao.ProdutoDAO;
import br.pucpcaldas.inf.lc.deliverit.model.Produto;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProdutoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        MySqlController conexao = (MySqlController) session.getAttribute("conexao");
        ProdutoDAO produtodao = new ProdutoDAO(conexao);
        switch (request.getParameter("action")) {

            case "Excluir":
                produtodao.removerProduto(Integer.parseInt(request.getParameter("codProduto")));
                response.sendRedirect("produto.jsp");
                break;
            case "Alterar":
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/autenticado/alteraProduto.jsp");
                request.setAttribute("codProduto", request.getParameter("codProduto"));
                request.setAttribute("nomeProduto", request.getParameter("nomeProduto"));
                request.setAttribute("precoProduto", request.getParameter("precoProduto"));
                request.setAttribute("quantidadeProduto", request.getParameter("quantidadeProduto"));
                request.setAttribute("categoriaProduto", request.getParameter("categoriaProduto"));              
                rd.forward(request, response);
                break;
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        MySqlController conexao = (MySqlController) session.getAttribute("conexao");
        Produto produto;
        ProdutoDAO produtodao = new ProdutoDAO(conexao);
        
        switch (request.getParameter("button")) {
            case "cadastrar":
                produto = new Produto(request.getParameter("nomeProduto"), Integer.parseInt(request.getParameter("quantidadeProduto")),
                        Float.parseFloat(request.getParameter("precoProduto")), Integer.parseInt(request.getParameter("categoriaProduto")));
                produtodao.cadastrarProduto(produto);
                response.sendRedirect("produto.jsp");
                break;
                
            case "alterar":
                 produto = new Produto(Integer.parseInt(request.getParameter("codProduto")),request.getParameter("nomeProduto"), Integer.parseInt(request.getParameter("quantidadeProduto")),
                        Float.parseFloat(request.getParameter("precoProduto")), Integer.parseInt(request.getParameter("categoriaProduto")));
                System.out.println("CodPROD: "+produto.getCodProduto() + " codcateg "+produto.getCodCategoria() + " preco prod " +produto.getPrecoProduto() + produto.getQtdEstoque() + produto.getNomeProduto()   );
                produtodao.atualizaProduto(produto);
                response.sendRedirect("produto.jsp");
                break;
                
        }        
    }

}
