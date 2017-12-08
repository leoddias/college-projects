package br.pucpcaldas.inf.lc.deliverit.controller;

import br.pucpcaldas.inf.lc.deliverit.dao.FluxoCaixaDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "fluxoCaixaController", urlPatterns = {"/fluxoCaixaController"})
public class FluxoCaixaController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        MySqlController conexao = (MySqlController) session.getAttribute("conexao");
        FluxoCaixaDAO registrodao = new FluxoCaixaDAO(conexao);
        boolean movimentacao;

        if ("entrada".equals(request.getParameter("movimentacao"))) {
            movimentacao = true;
        } else {
            movimentacao = false;
        }
        if (request.getParameter("button") != null) {
            switch (request.getParameter("button")) {
                case "registrar":
                    registrodao.criarRegistro(registrodao.getData(), request.getParameter("descricao"), movimentacao, Float.parseFloat(request.getParameter("valor")));
                    response.sendRedirect("fluxoCaixa.jsp");
                    break;
                case "visualizar":
                    request.setAttribute("dataBusca", request.getParameter("dataBusca"));
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/autenticado/fluxoCaixa.jsp");
                    rd.forward(request, response);
                    break;
            }

        }

    }

}
