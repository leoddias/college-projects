package br.pucpcaldas.inf.lc.deliverit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        MySqlController conexao = (MySqlController) session.getAttribute("conexao");
        conexao.Fechar();
        session.invalidate();

        response.sendRedirect("../index.jsp");


    }
}
