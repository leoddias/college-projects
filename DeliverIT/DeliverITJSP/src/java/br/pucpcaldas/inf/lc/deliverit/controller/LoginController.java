package br.pucpcaldas.inf.lc.deliverit.controller;

import br.pucpcaldas.inf.lc.deliverit.dao.LoginDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MySqlController conexao = null;
        PrintWriter out = response.getWriter();
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        try {
            conexao = new MySqlController();
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-danger'><p class='text-danger text-center'>SEM CONEXÃO COM O BANCO DE DADOS!</p></div>");
            rd.include(request, response);
        } catch (ClassNotFoundException cnfe) {
            out.println("<div class='alert alert-danger'><p class='text-danger text-center'>SEM CONEXÃO COM O BANCO DE DADOS!</p></div>");
            rd.include(request, response);

        }
        LoginDAO logindao = new LoginDAO(conexao);
        try {
            if (conexao != null && logindao.CheckLogin(usuario, senha)) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", logindao.getUsu().getName());
                session.setAttribute("tipo", logindao.getUsu().getTipo());
                session.setAttribute("estabelecimento", logindao.getUsu().getTipo());
                session.setAttribute("conexao", conexao);
                session.setMaxInactiveInterval(60 * 60); // 60 minutos de inatividade                
                response.sendRedirect("autenticado/index.jsp");
            } else {
                out.println("<div class='alert alert-danger'><p class='text-danger text-center'>Usuário ou senha inválidos!</p></div>");
                rd.include(request, response);
            }
        } catch (SQLException ex) {
            out.println("<div class='alert alert-danger'><p class='text-danger text-center'>SEM CONEXÃO COM O BANCO DE DADOS!</p></div>");
            rd.include(request, response);

        }

    }

}
