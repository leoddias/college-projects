package br.pucpcaldas.inf.lc.deliverit.controller;

import br.pucpcaldas.inf.lc.deliverit.dao.CategoriaDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CategoriaController", urlPatterns = {"/CategoriaController"})
public class CategoriaController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        MySqlController conexao = (MySqlController) session.getAttribute("conexao");
        CategoriaDAO categoriadao = new CategoriaDAO(conexao);
        String botao = request.getParameter("button");
        switch (botao) {
            case "cadastrar":
                categoriadao.criarCategoria(request.getParameter("cadastrar"));
                break;
            case "alterar": {
                String novoNome = request.getParameter("novoNome");
                int codCategoria = Integer.parseInt(request.getParameter("alterarCombo"));
                categoriadao.atualizaCategoria(novoNome, codCategoria);
                break;
            }
            case "remover": {                  
                int codCategoria = Integer.parseInt(request.getParameter("alterarCombo"));
                categoriadao.removerCategoria(codCategoria);
                break;
            }
        }
        response.sendRedirect("categoria.jsp");

    }
}
