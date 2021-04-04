package ensta.servlet;

import ensta.service.LivreService;
import ensta.service.impl.LivreServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LivreAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/livre_add")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_add.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LivreService livreService = LivreServiceImpl.getInstance();
        int id = -1;
        try {
            if (request.getParameter("titre") == null){
                throw new ServletException("Titre vide!");
            }else{
                String titre = new String(request.getParameter("titre").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                String auteur = new String(request.getParameter("auteur").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                id = livreService.create(titre, auteur, request.getParameter("isbn"));
                request.setAttribute("id", id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/livre_details?id=" + id);
    }
}